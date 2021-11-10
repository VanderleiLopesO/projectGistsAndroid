package com.example.projectgistsandroid.presentation.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.projectgistsandroid.presentation.entities.ViewGist

interface OnItemClickListener {
    fun onGistClicked(gist: ViewGist)
    fun onRetryRequestClicked()
}

abstract class BaseBrowserViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: Any, position: Int)

}