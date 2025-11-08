package com.desager.catslist.ui.screens.main_list.adapter

import androidx.viewbinding.ViewBinding
import com.desager.catslist.domain.model.Item
import com.desager.catslist.ui.adapter.BaseViewHolder
import com.desager.catslist.ui.adapter.FingerprintAdapter
import com.desager.catslist.ui.adapter.ItemFingerprint

class DynamicFingerprintAdapter(
    fingerprints: List<ItemFingerprint<*, *>>,
    private val onLoad: () -> Unit
) : FingerprintAdapter(fingerprints) {

    var isLoading = false

    override fun onViewAttachedToWindow(holder: BaseViewHolder<ViewBinding, Item>) {
        val pos = holder.bindingAdapterPosition
        val limit = itemCount - LOADING_OFFSET - 1

        if (pos >= limit && !isLoading) {
            isLoading = true
            onLoad()
        }
    }

    companion object {

        private const val LOADING_OFFSET = 1
    }
}