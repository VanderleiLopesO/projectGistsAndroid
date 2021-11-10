package com.example.projectgistsandroid.presentation.adapters.viewholders

import com.example.projectgistsandroid.databinding.ErrorListItemBinding

class ErrorViewHolder(
    private val binding: ErrorListItemBinding,
    private val listener: OnItemClickListener
) :
    BaseBrowserViewHolder(binding) {

    override fun bind(item: Any, position: Int) {
        itemView.apply {
            binding.errorText.setOnClickListener {
                listener.onRetryRequestClicked()
            }
        }
    }

}