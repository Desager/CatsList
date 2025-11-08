package com.desager.catslist.ui.screens.main_list

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
import com.desager.catslist.databinding.FragmentMainListBinding
import com.desager.catslist.mvi.view.MviFragment
import com.desager.catslist.ui.adapter.fingerprints.CatFingerprint
import com.desager.catslist.ui.screens.main_list.action.MainListAction
import com.desager.catslist.ui.screens.main_list.adapter.DynamicFingerprintAdapter
import com.desager.catslist.ui.screens.main_list.event.MainListEvent
import com.desager.catslist.ui.screens.main_list.state.MainListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainListFragment : MviFragment<MainListViewmodel, MainListState, MainListEvent, MainListAction>() {

    override val viewModel: MainListViewmodel by viewModels()

    private var _binding: FragmentMainListBinding? = null
    private val binding get() = _binding!!

    private val adapter = DynamicFingerprintAdapter(
        fingerprints = getFingerprints(),
        onLoad = {
            Log.d(TAG, "Adapter onLoad() invoke")
            viewModel.handleEvent(MainListEvent.LoadCats(LOADING_LIMIT))
        }
    )

    private val navController by lazy { findNavController() }

    override fun renderState(state: MainListState) {
        Log.d(TAG, "State changed, renderState() invoke. State: $state")

        adapter.isLoading = state.isLoading
        adapter.submitList(state.cats)
    }

    override fun renderAction(action: MainListAction) {
        when(action) {
            is MainListAction.NavigateToDetails -> {
                Log.d(TAG, "Navigating to CatDetailsFragment")
                val direction = MainListFragmentDirections.actionMainToDetails(action.model)
                navController.navigate(direction)
            }
        }
    }

    override fun provideView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@MainListFragment.adapter

            val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider, requireActivity().theme)
                ?.let { dividerItemDecoration.setDrawable(it) }
            addItemDecoration(dividerItemDecoration)
        }

        if (adapter.currentList.isEmpty()) {
            viewModel.handleEvent(MainListEvent.LoadCats(LOADING_LIMIT))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getFingerprints() = listOf(
        CatFingerprint { viewModel.handleEvent(MainListEvent.NavigateToDetails(it)) }
    )

    companion object {
        private const val TAG = "MAIN_LIST_FRAGMENT"
        private const val LOADING_LIMIT = 5
    }
}