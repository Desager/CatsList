package com.desager.catslist.ui.screens.cat_details.event

import com.desager.catslist.mvi.view.ViewEvent

sealed interface CatDetailsEvent : ViewEvent {

    object SetIsLiked : CatDetailsEvent
}