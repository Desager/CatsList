package com.desager.catslist.features.favourite_list.action

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureAction
import kotlinx.coroutines.flow.Flow

sealed interface FavouriteListFeatureAction : FeatureAction {

    class NavigateToDetails(val model: CatModel) : FavouriteListFeatureAction

    class CollectFlow(val flow: Flow<List<CatModel>>) : FavouriteListFeatureAction
}