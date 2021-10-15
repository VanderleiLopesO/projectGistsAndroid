package com.example.projectgistsandroid.presentation.adapters.viewholders

import android.view.View

class ErrorViewHolder(itemView: View, private val listener: OnItemClickListener) :
    BaseBrowserViewHolder(itemView) {

    override fun bind(item: Any, position: Int) {
        itemView.apply {
            errorText.setOnClickListener {
                listener.onRetryRequestClicked()
            }
        }
    }

}