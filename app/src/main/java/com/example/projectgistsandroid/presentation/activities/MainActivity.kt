package com.example.projectgistsandroid.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.projectgistsandroid.R
import com.example.projectgistsandroid.data.repositories.interfaces.GistRepository
import com.example.projectgistsandroid.databinding.ActivityMainBinding
import com.example.projectgistsandroid.domain.usecases.interfaces.GetGists
import com.example.projectgistsandroid.presentation.viewmodels.Loaded
import com.example.projectgistsandroid.presentation.viewmodels.Loading
import com.example.projectgistsandroid.presentation.viewmodels.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
    }

    private fun setUpViewModel() {

        viewModel.run {
            requestData()

            status.observe(this@MainActivity, Observer {
                when (it) {
                    Loading -> showProgressBar()
                    Loaded -> showRepositories()
                    else -> showError()
                }
            })

            nextPageData.observe(this@MainActivity, Observer { items ->
                val tempList = (binding.gistsRecyclerView.adapter as MainAdapter).list

                items?.forEach {
                    tempList.add(it)
                    data.value?.plus(it)
                    (binding.gistsRecyclerView.adapter as MainAdapter).apply {
                        list = tempList
                        notifyDataSetChanged()
                    }
                }
            })

            nextPageStatus.observe(this@MainActivity, Observer {
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
            list.add(LoadingItem)
            notifyDataSetChanged()
        }
    }

    private fun hidePaginationLoading() {
        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.remove(LoadingItem)
            notifyDataSetChanged()
        }
    }

    private fun showPaginationError() {
        hidePaginationLoading()

        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.add(ErrorItem)
            notifyDataSetChanged()
        }
    }

    private fun hidePaginationError() {
        (binding.gistsRecyclerView.adapter as MainAdapter).apply {
            list.remove(ErrorItem)
            notifyDataSetChanged()
        }
    }

    private fun showRepositories() {
        viewModel.data.value?.let {
            if (viewModel.page == 1) {
                (binding.gistsRecyclerView.adapter as MainAdapter).list = it.items.toMutableList()
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


}