package com.desager.catslist.features.cat_details

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.cat_details.action.CatDetailsFeatureAction
import com.desager.catslist.features.cat_details.actors.SetIsLikedActor
import com.desager.catslist.features.cat_details.event.CatDetailsFeatureEvent
import com.desager.catslist.features.cat_details.reducer.CatDetailsReducer
import com.desager.catslist.features.cat_details.state.CatDetailsFeatureState
import com.desager.catslist.mvi.domain.DelayedEvent
import com.desager.catslist.mvi.domain.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class CatDetailsFeature(
    repository: CatsRepository,
    coroutineScope: CoroutineScope
) : Feature<CatDetailsFeatureState, CatDetailsFeatureEvent, CatDetailsFeatureAction>(coroutineScope) {

    override val _stateFlow = MutableStateFlow(CatDetailsFeatureState.default)
    override val eventFlow = MutableSharedFlow<CatDetailsFeatureEvent>()
    override val _actionFlow = MutableSharedFlow<CatDetailsFeatureAction>()

    private val delayedEvent = DelayedEvent<CatDetailsFeatureEvent> {
        handleEvent(it)
    }

    override val reducer = CatDetailsReducer()
    override val actors = setOf(
        SetIsLikedActor(repository, coroutineScope, delayedEvent)
    )

    init {
        handleSideEvents()
    }
}