package com.desager.catslist.features.cat_details.reducer

import com.desager.catslist.features.cat_details.action.CatDetailsFeatureAction
import com.desager.catslist.features.cat_details.event.CatDetailsFeatureEvent
import com.desager.catslist.features.cat_details.state.CatDetailsFeatureState
import com.desager.catslist.mvi.domain.Reducer

class CatDetailsReducer : Reducer<CatDetailsFeatureState, CatDetailsFeatureEvent, CatDetailsFeatureAction> {

    override fun reduce(
        event: CatDetailsFeatureEvent,
        state: CatDetailsFeatureState
    ): Pair<CatDetailsFeatureState?, CatDetailsFeatureAction?> {
        var newState: CatDetailsFeatureState? = null
        var newAction: CatDetailsFeatureAction? = null

        when(event) {
            is CatDetailsFeatureEvent.LikedSet -> {
                newState = state.copy(
                    catModel = state.catModel.copy(
                        isLiked = !state.catModel.isLiked
                    )
                )
            }
            else -> Unit
        }

        return newState to newAction
    }
}