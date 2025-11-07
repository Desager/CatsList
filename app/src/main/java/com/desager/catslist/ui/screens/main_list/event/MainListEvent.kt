package com.desager.catslist.ui.screens.main_list.event

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewEvent

sealed interface MainListEvent : ViewEvent {

    class LoadCats(val limit: Int) : MainListEvent

    class NavigateToDetails(val model: CatModel) : MainListEvent
}