package com.desager.catslist.ui.adapter.fingerprints

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.desager.catslist.R
import com.desager.catslist.databinding.ItemBreedBinding
import com.desager.catslist.domain.model.BreedModel
import com.desager.catslist.domain.model.Item
import com.desager.catslist.ui.adapter.BaseViewHolder
import com.desager.catslist.ui.adapter.ItemFingerprint

class BreedFingerprint : ItemFingerprint<ItemBreedBinding, BreedModel> {

    override fun isRelativeItem(item: Item) = item is BreedModel

    override fun getLayoutId() = R.layout.item_breed

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemBreedBinding, BreedModel> {
        val binding = ItemBreedBinding.inflate(layoutInflater, parent, false)
        return BreedViewHolder(binding)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<BreedModel>() {

        override fun areItemsTheSame(
            oldItem: BreedModel,
            newItem: BreedModel
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: BreedModel,
            newItem: BreedModel
        ): Boolean = oldItem == newItem
    }
}

class BreedViewHolder(
    binding: ItemBreedBinding
) : BaseViewHolder<ItemBreedBinding, BreedModel>(binding) {

    override fun onBind(item: BreedModel) {
        super.onBind(item)

        with(binding) {
            val context = binding.root.context

            name.text = context.getString(R.string.cat_name, item.name)
            temperament.text = context.getString(R.string.cat_temperament, item.temperament)
            origin.text = context.getString(R.string.cat_origin, item.origin)
            description.text = context.getString(R.string.cat_description, item.description)
        }
    }
}