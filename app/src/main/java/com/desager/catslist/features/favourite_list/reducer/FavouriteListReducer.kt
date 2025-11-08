package com.desager.catslist.features.favourite_list.reducer

import com.desager.catslist.features.favourite_list.action.FavouriteListFeatureAction
import com.desager.catslist.features.favourite_list.event.FavouriteListFeatureEvent
import com.desager.catslist.features.favourite_list.state.FavouriteListFeatureState
import com.desager.catslist.mvi.domain.Reducer

class FavouriteListReducer : Reducer<FavouriteListFeatureState, FavouriteListFeatureEvent, FavouriteListFeatureAction> {

    override fun reduce(
        event: FavouriteListFeatureEvent,
        state: FavouriteListFeatureState
    ): Pair<FavouriteListFeatureState?, FavouriteListFeatureAction?> {
        var newState: FavouriteListFeatureState? = null
        var newAction: FavouriteListFeatureAction? = null

        when(event) {
            is FavouriteListFeatureEvent.NavigateToDetails -> {
                newAction = FavouriteListFeatureAction.NavigateToDetails(event.model)
            }
            is FavouriteListFeatureEvent.ReturnCatsFlow -> {
                newAction = FavouriteListFeatureAction.CollectFlow(event.flow)
            }
            else -> Unit
        }

        return newState to newAction
    }
}