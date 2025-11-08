package com.desager.catslist.features.main_list.reducer

import com.desager.catslist.features.main_list.action.MainListFeatureAction
import com.desager.catslist.features.main_list.event.MainListFeatureEvent
import com.desager.catslist.features.main_list.state.MainListFeatureState
import com.desager.catslist.mvi.domain.Reducer

class MainListFeatureReducer : Reducer<MainListFeatureState, MainListFeatureEvent, MainListFeatureAction> {

    override fun reduce(
        event: MainListFeatureEvent,
        state: MainListFeatureState
    ): Pair<MainListFeatureState?, MainListFeatureAction?> {
        var newAction: MainListFeatureAction? = null
        var newState: MainListFeatureState? = null

        when(event) {
            is MainListFeatureEvent.Loading -> {
                newState = state.copy(
                    isLoading = true
                )
            }
            is MainListFeatureEvent.NavigateToDetails -> {
                newAction = MainListFeatureAction.NavigateToDetails(event.model)
            }
            is MainListFeatureEvent.ReturnCats -> {
                newState = state.copy(
                    isLoading = false,
                    cats = state.cats + event.list
                )
            }
            else -> Unit
        }

        return newState to newAction
    }
}