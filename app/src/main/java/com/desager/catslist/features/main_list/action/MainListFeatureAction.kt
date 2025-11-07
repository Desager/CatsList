package com.desager.catslist.features.main_list.action

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureAction

sealed interface MainListFeatureAction : FeatureAction {

    class NavigateToDetails(val model: CatModel) : MainListFeatureAction
}