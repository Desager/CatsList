package com.desager.catslist.ui.adapter.fingerprints

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.desager.catslist.R
import com.desager.catslist.databinding.ItemCatBinding
import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.domain.model.Item
import com.desager.catslist.ui.adapter.BaseViewHolder
import com.desager.catslist.ui.adapter.ItemFingerprint

class CatFingerprint(
    private val onClick: (CatModel) -> Unit
) : ItemFingerprint<ItemCatBinding, CatModel> {

    override fun isRelativeItem(item: Item) = item is CatModel

    override fun getLayoutId() = R.layout.item_cat

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<ItemCatBinding, CatModel> {
        val binding = ItemCatBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(binding, onClick)
    }

    override fun getDiffUtil() = diffUtil

    private val diffUtil = object : DiffUtil.ItemCallback<CatModel>() {

        override fun areItemsTheSame(
            oldItem: CatModel,
            newItem: CatModel
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CatModel,
            newItem: CatModel
        ): Boolean = oldItem == newItem
    }
}

class CatViewHolder(
    binding: ItemCatBinding,
    private val onClick: (CatModel) -> Unit
) : BaseViewHolder<ItemCatBinding, CatModel>(binding) {

    init {
        binding.root.setOnClickListener {
            onClick(item)
        }
    }

    override fun onBind(item: CatModel) {
        super.onBind(item)

        with(binding) {
            val context = root.context

            val diskCacheStrategy = if (item.isLiked) {
                DiskCacheStrategy.ALL
            } else {
                DiskCacheStrategy.NONE
            }

            Glide.with(root)
                .load(item.url)
                .centerCrop()
                .placeholder(R.drawable.cat_placeholder)
                .diskCacheStrategy(diskCacheStrategy)
                .into(image)

            val breed = item.breeds.firstOrNull()

            if (breed != null) {
                name.apply {
                    visibility = View.VISIBLE
                    text = context.getString(R.string.cat_name, breed.name)
                }
                temperament.apply {
                    visibility = View.VISIBLE
                    text = context.getString(R.string.cat_temperament, breed.temperament)
                }
                origin.apply {
                    visibility = View.VISIBLE
                    text = context.getString(R.string.cat_origin, breed.origin)
                }
                description.apply {
                    visibility = View.VISIBLE
                    text = context.getString(R.string.cat_description, breed.description)
                }
            } else {
                name.visibility = View.INVISIBLE
                temperament.visibility = View.INVISIBLE
                origin.visibility = View.INVISIBLE
                description.visibility = View.INVISIBLE
            }
        }
    }
}