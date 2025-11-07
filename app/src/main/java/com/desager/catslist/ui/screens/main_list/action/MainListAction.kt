package com.desager.catslist.ui.screens.main_list.action

import com.desager.catslist.domain.model.CatModel
import com.desager.catslist.mvi.view.ViewAction

sealed interface MainListAction : ViewAction {

    class NavigateToDetails(val model: CatModel) : MainListAction
}