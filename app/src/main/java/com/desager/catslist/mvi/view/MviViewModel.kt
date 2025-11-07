package com.desager.catslist.mvi.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desager.catslist.mvi.domain.Feature
import com.desager.catslist.mvi.domain.FeatureAction
import com.desager.catslist.mvi.domain.FeatureEvent
import com.desager.catslist.mvi.domain.FeatureState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class MviViewModel<
        VS: ViewState, VE: ViewEvent, VA: ViewAction,
        FS: FeatureState, FE: FeatureEvent, FA: FeatureAction> : ViewModel() {

    protected abstract val _stateFlow: MutableStateFlow<VS>
    val stateFlow get() = _stateFlow.asStateFlow()

    protected abstract val _actionFlow: MutableSharedFlow<VA>
    val actionFlow get() =  _actionFlow.asSharedFlow()

    protected abstract val feature: Feature<FS, FE, FA>

    protected abstract fun viewStateToFeatureState(state: VS): FS
    protected abstract fun featureStateToViewState(state: FS): VS
    protected abstract fun viewEventToFeatureEvent(event: VE): FE?
    protected abstract fun featureActionToViewAction(action: FA): VA?

    protected fun collectFeatureFlows() {
        viewModelScope.launch {
            launch {
                feature.stateFlow
                    .filterNotNull()
                    .map { featureStateToViewState(it) }
                    .collect { _stateFlow.emit(it) }
            }

            launch {
                feature.actionFlow
                    .filterNotNull()
                    .map { featureActionToViewAction(it) }
                    .filterNotNull()
                    .collect { _actionFlow.emit(it) }
            }
        }
    }

    fun handleEvent(event: VE) {
        val featureEvent = viewEventToFeatureEvent(event) ?: return
        val featureState = viewStateToFeatureState(stateFlow.value)

        feature.handleEvent(featureEvent, featureState)
    }
}