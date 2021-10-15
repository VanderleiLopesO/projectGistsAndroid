package com.example.projectgistsandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectgistsandroid.R
import com.example.projectgistsandroid.domain.entities.GistItem
import com.example.projectgistsandroid.presentation.adapters.viewholders.BaseBrowserViewHolder
import com.example.projectgistsandroid.presentation.adapters.viewholders.ErrorViewHolder
import com.example.projectgistsandroid.presentation.adapters.viewholders.LoadingViewHolder
import com.example.projectgistsandroid.presentation.adapters.viewholders.OnItemClickListener
import com.lopessoft.projectgithublabs.presentation.adapters.viewholders.*

class MainAdapter : RecyclerView.Adapter<BaseBrowserViewHolder>() {

    var list = mutableListOf<GistItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBrowserViewHolder =
        when (viewType) {
            REPOSITORY_TYPE -> RepositoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.repository_list_item, parent, false),
                listener
            )
            PULL_REQUEST_TYPE -> PullRequestViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.pull_request_list_item, parent, false),
                listener
            )
            LOADING_TYPE -> LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.loading_list_item, parent, false)
            )
            else -> ErrorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.error_list_item, parent, false),
                listener
            )
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseBrowserViewHolder, position: Int) =
        holder.bind(list[position], position)

    override fun getItemViewType(position: Int): Int =
        when (list[position]) {
            is Item -> REPOSITORY_TYPE
            is PullRequestItem -> PULL_REQUEST_TYPE
            is LoadingItem -> LOADING_TYPE
            is ErrorItem -> ERROR_TYPE
        }

    companion object {
        const val REPOSITORY_TYPE = 0
        const val PULL_REQUEST_TYPE = 1
        const val LOADING_TYPE = 2
        const val ERROR_TYPE = 3
    }

}
