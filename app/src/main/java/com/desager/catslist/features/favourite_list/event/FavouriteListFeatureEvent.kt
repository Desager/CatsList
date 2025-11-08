package com.desager.catslist.features.favourite_list.event

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureEvent
import kotlinx.coroutines.flow.Flow

sealed interface FavouriteListFeatureEvent : FeatureEvent {

    object GetCatsFlow : FavouriteListFeatureEvent

    class NavigateToDetails(val model: CatModel) : FavouriteListFeatureEvent

    class ReturnCatsFlow(val flow: Flow<List<CatModel>>) : FavouriteListFeatureEvent
}