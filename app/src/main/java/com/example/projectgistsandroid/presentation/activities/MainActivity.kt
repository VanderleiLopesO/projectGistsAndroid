package com.example.projectgistsandroid.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectgistsandroid.databinding.ActivityMainBinding
import com.example.projectgistsandroid.presentation.adapters.MainAdapter
import com.example.projectgistsandroid.presentation.adapters.viewholders.OnItemClickListener
import com.example.projectgistsandroid.presentation.entities.PresentationEntities
import com.example.projectgistsandroid.presentation.entities.ViewError
import com.example.projectgistsandroid.presentation.entities.ViewGist
import com.example.projectgistsandroid.presentation.entities.ViewLoading
import com.example.projectgistsandroid.presentation.viewmodels.Loaded
import com.example.projectgistsandroid.presentation.viewmodels.Loading
import com.example.projectgistsandroid.presentation.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpViewModel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveViewModelState()

        super.onSaveInstanceState(outState)
    }

    private fun setUpRecyclerView() {
        binding.gistsRecyclerView.apply {

            val mainAdapter = MainAdapter()
            mainAdapter.listener = this@MainActivity

            adapter = mainAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        val visibleItemCount = (layoutManager as LinearLayoutManager).childCount
                        val totalItemCount = (layoutManager as LinearLayoutManager).itemCount
                        val pastVisibleItems =
                            (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            if ((viewModel.page < viewModel.data.value?.size!!) &&
                                viewModel.nextPageStatus.value != Loading) {
                                viewModel.requestNextPageData()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun setUpViewModel() {

        viewModel.run {
            requestData()

            status.observe(this@MainActivity, {
                when (it) {
                    Loading -> showProgressBar()
                    Loaded -> showRepositories()
                    else -> showError()
                }
            })

            nextPageData.observe(this@MainActivity, { items ->
                val tempList = (binding.gistsRecyclerView.adapter as MainAdapter).list

                items?.forEach {
                    it?.let {
                        tempList.add(it)
                        data.value?.plus(it)
                        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
                            list = tempList
                            notifyDataSetChanged()
                        }
                    }
                }
            })

            nextPageStatus.observe(this@MainActivity, {
                when (it) {
                    Loading -> showPaginationLoading()
                    Loaded -> hidePaginationLoading()
                    else -> showPaginationError()
                }
            })

        }
    }

    private fun showProgressBar() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            gistsRecyclerView.visibility = View.GONE
            errorText.visibility = View.GONE
        }
    }

    private fun showPaginationLoading() {
        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.add(ViewLoading)
            notifyDataSetChanged()
        }
    }

    private fun hidePaginationLoading() {
        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.remove(ViewLoading)
            notifyDataSetChanged()
        }
    }

    private fun showPaginationError() {
        hidePaginationLoading()

        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.add(ViewError)
            notifyDataSetChanged()
        }
    }

    private fun hidePaginationError() {
        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.remove(ViewError)
            notifyDataSetChanged()
        }
    }

    private fun showRepositories() {
        println("DEUBOMMMMMMMMMMMMMM ======= TUTUSTUUSTUSUTUSTUSUTUSTS")

        viewModel.data.value?.let { list ->
            if (viewModel.page == 1) {
                    list.toMutableList().let { it ->
                        val resultList = mutableListOf<PresentationEntities>()
                        it.forEach {
                            it?.let {
                                resultList.add(it)
                            }
                        }

                        (binding.gistsRecyclerView.adapter as MainAdapter).list = resultList
                    }
            }

            binding.progressBar.visibility = View.GONE
            binding.gistsRecyclerView.visibility = View.VISIBLE
            binding.errorText.visibility = View.GONE
        } ?: kotlin.run {
            showError()
        }
    }

    private fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.gistsRecyclerView.visibility = View.GONE
        binding.errorText.visibility = View.VISIBLE

        binding.errorText.setOnClickListener {
            viewModel.retryRequest(true)
        }
    }

    override fun onGistClicked(gist: ViewGist) {
        startActivity(Intent(this, DetailsActivity::class.java))
    }

    override fun onRetryRequestClicked() {
        viewModel.retryRequest(false)
    }

}