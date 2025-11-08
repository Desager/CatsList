package com.desager.catslist.ui.screens.favourite_list.action

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewAction

interface FavouriteListAction : ViewAction {

    class NavigateToDetails(val model: CatModel) : FavouriteListAction
}