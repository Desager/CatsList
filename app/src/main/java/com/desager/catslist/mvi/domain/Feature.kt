package com.desager.catslist.mvi.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.launch

abstract class Feature<S : FeatureState, E : FeatureEvent, A : FeatureAction>(
    private val coroutineScope: CoroutineScope
) {

    protected abstract val _stateFlow: MutableStateFlow<S>
    val stateFlow get() = _stateFlow.asStateFlow()

    protected abstract val eventFlow: MutableSharedFlow<E>

    protected abstract val _actionFlow: MutableSharedFlow<A>
    val actionFlow get() = _actionFlow.asSharedFlow()

    protected abstract val reducer: Reducer<S, E, A>

    protected abstract val actors: Set<Actor<E>>

    protected fun handleSideEvents() {
        coroutineScope.launch {
            eventFlow
                .buffer()
                .collect { event ->
                    launch {
                        actors.forEach {
                            it.process(event)?.let { newEvent ->
                                eventFlow.emit(newEvent)
                            }
                        }
                    }

                    val (newState, newAction) = reducer.reduce(event, stateFlow.value)
                    newState?.let { _stateFlow.emit(it) }
                    newAction?.let { _actionFlow.emit(it) }
                }
        }
    }

    fun handleEvent(event: E, state: S) {
        coroutineScope.launch {
            eventFlow.emit(event)
            _stateFlow.emit(state)
        }
    }
}