package com.desager.catslist.ui.screens.favourite_list.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewState

data class FavouriteListState(
    val cats: List<CatModel>
) : ViewState {

    companion object {
        val default = FavouriteListState(
            cats = emptyList()
        )
    }
}
