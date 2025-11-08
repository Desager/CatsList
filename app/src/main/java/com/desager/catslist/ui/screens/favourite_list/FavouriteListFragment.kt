package com.desager.catslist.ui.screens.favourite_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.desager.catslist.R
import com.desager.catslist.databinding.FragmentFavouriteListBinding
import com.desager.catslist.mvi.view.MviFragment
import com.desager.catslist.ui.adapter.FingerprintAdapter
import com.desager.catslist.ui.adapter.fingerprints.CatFingerprint
import com.desager.catslist.ui.screens.favourite_list.action.FavouriteListAction
import com.desager.catslist.ui.screens.favourite_list.event.FavouriteListEvent
import com.desager.catslist.ui.screens.favourite_list.state.FavouriteListState
import com.desager.catslist.ui.util.toUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteListFragment : MviFragment<FavouriteListViewmodel, FavouriteListState, FavouriteListEvent, FavouriteListAction>() {

    override val viewModel: FavouriteListViewmodel by viewModels()

    private var _binding: FragmentFavouriteListBinding? = null
    private val binding get() = _binding!!

    private val adapter = FingerprintAdapter(
        fingerprints = getFingerprints()
    )

    private val navController by lazy { findNavController() }

    override fun renderState(state: FavouriteListState) {
        Log.d(TAG, "State changed, renderState() invoke. State: $state")

        adapter.submitList(state.cats)
    }

    override fun renderAction(action: FavouriteListAction) {
        when(action) {
            is FavouriteListAction.NavigateToDetails -> {
                Log.d(TAG, "Navigating to CatDetailsFragment")
                val direction = FavouriteListFragmentDirections.actionFavouriteToDetails(action.model.toUiModel())
                navController.navigate(direction)
            }
        }
    }

    override fun provideView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        _binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@FavouriteListFragment.adapter

            val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider, requireActivity().theme)
                ?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFingerprints() = listOf(
        CatFingerprint { viewModel.handleEvent(FavouriteListEvent.NavigateToDetails(it)) }
    )

    companion object {
        private const val TAG = "FAVOURITE_LIST_FRAGMENT"
    }
}