package com.desager.catslist.features.main_list.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.domain.FeatureState

data class MainListFeatureState(
    val isLoading: Boolean,
    val cats: List<CatModel>
) : FeatureState {

    companion object {
        val default = MainListFeatureState(
            isLoading = false,
            cats = emptyList()
        )
    }
}