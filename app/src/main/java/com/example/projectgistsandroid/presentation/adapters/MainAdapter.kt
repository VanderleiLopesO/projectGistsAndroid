package com.example.projectgistsandroid.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectgistsandroid.databinding.ErrorListItemBinding
import com.example.projectgistsandroid.databinding.LoadingListItemBinding
import com.example.projectgistsandroid.databinding.RepositoryListItemBinding
import com.example.projectgistsandroid.presentation.adapters.viewholders.*
import com.example.projectgistsandroid.presentation.entities.PresentationEntities
import com.example.projectgistsandroid.presentation.entities.ViewError
import com.example.projectgistsandroid.presentation.entities.ViewGist
import com.example.projectgistsandroid.presentation.entities.ViewLoading

class MainAdapter : RecyclerView.Adapter<BaseBrowserViewHolder>() {

    var list = mutableListOf<PresentationEntities>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBrowserViewHolder =
        when (viewType) {
            GIST_TYPE -> GistViewHolder(
                parent.context,
                RepositoryListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                listener
            )
            LOADING_TYPE -> LoadingViewHolder(
                LoadingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> ErrorViewHolder(
                ErrorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                listener
            )
        }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseBrowserViewHolder, position: Int) =
        holder.bind(list[position], position)

    override fun getItemViewType(position: Int) =
        when (list[position]) {
            is ViewGist -> GIST_TYPE
            is ViewError -> ERROR_TYPE
            is ViewLoading -> LOADING_TYPE
        }

    companion object {
        const val GIST_TYPE = 0
        const val ERROR_TYPE = 1
        const val LOADING_TYPE = 2
    }

}
