package com.example.projectgistsandroid.presentation.adapters.viewholders

import android.content.Context
import com.example.projectgistsandroid.databinding.RepositoryListItemBinding
import com.example.projectgistsandroid.presentation.entities.ViewGist
import com.example.projectgistsandroid.presentation.extensions.loadImage
import java.lang.Exception

class GistViewHolder(
    private val context: Context,
    private val binding: RepositoryListItemBinding,
    private val listener: OnItemClickListener?
) :
    BaseBrowserViewHolder(binding) {

    override fun bind(item: Any, position: Int) {
        try {
            val currentItem = (item as ViewGist)

            binding.run {
                repositoryNameTextView.text = currentItem.type
                repositoryDescriptionTextView.text = currentItem.description
                repositoryOwnerLoginTextView.text = currentItem.ownerLogin
                repositoryUserImage.loadImage(currentItem.ownerAvatarUrl, context)

                repositoryCellRoot.setOnClickListener {
                    listener?.onGistClicked(currentItem)
                }
            }

        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}