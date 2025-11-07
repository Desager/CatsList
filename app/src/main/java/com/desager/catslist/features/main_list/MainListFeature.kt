package com.desager.catslist.features.main_list

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.main_list.action.MainListFeatureAction
import com.desager.catslist.features.main_list.actors.LoadCatsActor
import com.desager.catslist.features.main_list.event.MainListFeatureEvent
import com.desager.catslist.features.main_list.reducer.MainListFeatureReducer
import com.desager.catslist.features.main_list.state.MainListFeatureState
import com.desager.catslist.mvi.domain.DelayedEvent
import com.desager.catslist.mvi.domain.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class MainListFeature(
    repository: CatsRepository,
    coroutineScope: CoroutineScope
) : Feature<MainListFeatureState, MainListFeatureEvent, MainListFeatureAction>(coroutineScope) {

    override val _stateFlow = MutableStateFlow(MainListFeatureState.default)
    override val eventFlow = MutableSharedFlow<MainListFeatureEvent>()
    override val _actionFlow = MutableSharedFlow<MainListFeatureAction>()

    private val delayedEvent = DelayedEvent<MainListFeatureEvent> {
        handleEvent(it, stateFlow.value)
    }

    override val reducer = MainListFeatureReducer()
    override val actors = setOf(
        LoadCatsActor(repository, coroutineScope,delayedEvent)
    )

    init {
        handleSideEvents()
    }
}