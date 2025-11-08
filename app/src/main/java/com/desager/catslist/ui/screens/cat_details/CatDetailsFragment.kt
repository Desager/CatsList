package com.desager.catslist.ui.screens.cat_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.desager.catslist.R
import com.desager.catslist.databinding.FragmentCatDetailsBinding
import com.desager.catslist.mvi.view.MviFragment
import com.desager.catslist.ui.adapter.FingerprintAdapter
import com.desager.catslist.ui.adapter.fingerprints.BreedFingerprint
import com.desager.catslist.ui.screens.cat_details.action.CatDetailsAction
import com.desager.catslist.ui.screens.cat_details.event.CatDetailsEvent
import com.desager.catslist.ui.screens.cat_details.state.CatDetailsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatDetailsFragment : MviFragment<CatDetailsViewmodel, CatDetailsState, CatDetailsEvent, CatDetailsAction>() {

    override val viewModel: CatDetailsViewmodel by viewModels()

    private var _binding: FragmentCatDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = FingerprintAdapter(
        fingerprints = getFingerprints()
    )

    private val navController by lazy { findNavController() }
    private val args: CatDetailsFragmentArgs by navArgs()

    override fun renderState(state: CatDetailsState) {
        Log.d(TAG, "State changed, renderState() invoke. State: $state")

        val catModel = state.catModel

        adapter.submitList(catModel.breeds)

        with(binding) {
            Glide.with(root)
                .load(catModel.url)
                .centerCrop()
                .placeholder(R.drawable.cat_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image)

            if (catModel.isLiked) {
                likeButton.text = getString(R.string.like)
                val drawable = resources.getDrawable(R.drawable.thumbs_up, requireContext().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                likeButton.setCompoundDrawables(drawable, null, null, null)
            } else {
                likeButton.text = getString(R.string.dislike)
                val drawable = resources.getDrawable(R.drawable.thumbs_down, requireContext().theme)
                drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
                likeButton.setCompoundDrawables(drawable, null, null, null)
            }
        }
    }

    override fun renderAction(action: CatDetailsAction) { }

    override fun provideView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        _binding = FragmentCatDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@CatDetailsFragment.adapter

            val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider, requireActivity().theme)
                ?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
        }

        val cat = args.catModel
        viewModel.handleState(
            state = CatDetailsState(
                catModel = cat
            )
        )

        binding.likeButton.setOnClickListener {
            viewModel.handleEvent(CatDetailsEvent.SetIsLiked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFingerprints() = listOf(
        BreedFingerprint()
    )

    companion object {
        private const val TAG = "CAT_DETAILS_FRAGMENT"
    }
}