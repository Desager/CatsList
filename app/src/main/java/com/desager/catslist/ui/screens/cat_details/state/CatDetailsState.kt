package com.desager.catslist.ui.screens.cat_details.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewState

data class CatDetailsState(
    val catModel: CatModel
) : ViewState {

    companion object {
        val default = CatDetailsState(
            catModel = CatModel(
                id = "",
                url = "",
                breeds = emptyList(),
                isLiked = false
            )
        )
    }
}