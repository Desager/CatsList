package com.desager.catslist.features.favourite_list

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.favourite_list.action.FavouriteListFeatureAction
import com.desager.catslist.features.favourite_list.actors.GetCatsFlowActor
import com.desager.catslist.features.favourite_list.event.FavouriteListFeatureEvent
import com.desager.catslist.features.favourite_list.reducer.FavouriteListReducer
import com.desager.catslist.features.favourite_list.state.FavouriteListFeatureState
import com.desager.catslist.mvi.domain.DelayedEvent
import com.desager.catslist.mvi.domain.Feature
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

class FavouriteListFeature(
    repository: CatsRepository,
    coroutineScope: CoroutineScope,
) : Feature<FavouriteListFeatureState, FavouriteListFeatureEvent, FavouriteListFeatureAction>(coroutineScope) {

    override val _stateFlow = MutableStateFlow(FavouriteListFeatureState.default)
    override val eventFlow = MutableSharedFlow<FavouriteListFeatureEvent>(replay = 1)
    override val _actionFlow = MutableSharedFlow<FavouriteListFeatureAction>()

    private val delayedEvent = DelayedEvent<FavouriteListFeatureEvent> {
        handleEvent(it)
    }

    override val reducer = FavouriteListReducer()
    override val actors = setOf(
        GetCatsFlowActor(repository, coroutineScope, delayedEvent)
    )

    init {
        handleSideEvents()
    }
}