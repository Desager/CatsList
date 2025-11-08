package com.desager.catslist.ui.screens.favourite_list.event

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewEvent

interface FavouriteListEvent : ViewEvent {

    object GetCatsFlow : FavouriteListEvent

    class NavigateToDetails(val model: CatModel) : FavouriteListEvent
}