package com.desager.catslist.features.main_list.event

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureEvent

sealed interface MainListFeatureEvent : FeatureEvent {

    class LoadCats(val limit: Int) : MainListFeatureEvent

    class NavigateToDetails(val model: CatModel) : MainListFeatureEvent

    object Loading : MainListFeatureEvent

    class ReturnCats(val list: List<CatModel>) : MainListFeatureEvent
}