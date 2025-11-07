package com.desager.catslist.mvi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

abstract class MviFragment<VM : MviViewModel<VS, VE, VA, *, *, *>,
        VS : ViewState, VE : ViewEvent, VA : ViewAction> : Fragment() {

    protected abstract val viewModel: VM

    protected abstract fun renderState(state: VS)
    protected abstract fun renderAction(action: VA)

    protected abstract fun provideView(inflater: LayoutInflater, container: ViewGroup?): View

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = provideView(inflater, container)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.stateFlow
                        .filterNotNull()
                        .collect { state ->
                            renderState(state)
                        }
                }

                launch {
                    viewModel.actionFlow
                        .filterNotNull()
                        .collect { action ->
                            renderAction(action)
                        }
                }
            }
        }
    }
}