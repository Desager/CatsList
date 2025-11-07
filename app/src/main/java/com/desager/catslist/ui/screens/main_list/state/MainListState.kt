package com.desager.catslist.ui.screens.main_list.state

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewState

data class MainListState(
    val isLoading: Boolean,
    val cats: List<CatModel>
) : ViewState {

    companion object {
        val default = MainListState(
            isLoading = false,
            cats = emptyList()
        )
    }
}