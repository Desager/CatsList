package com.desager.catslist.ui.screens.cat_details

import androidx.lifecycle.viewModelScope
import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.cat_details.CatDetailsFeature
import com.desager.catslist.features.cat_details.action.CatDetailsFeatureAction
import com.desager.catslist.features.cat_details.event.CatDetailsFeatureEvent
import com.desager.catslist.features.cat_details.state.CatDetailsFeatureState
import com.desager.catslist.mvi.view.MviViewModel
import com.desager.catslist.ui.screens.cat_details.action.CatDetailsAction
import com.desager.catslist.ui.screens.cat_details.event.CatDetailsEvent
import com.desager.catslist.ui.screens.cat_details.state.CatDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CatDetailsViewmodel @Inject constructor(
    repository: CatsRepository,
) : MviViewModel<CatDetailsState, CatDetailsEvent, CatDetailsAction, CatDetailsFeatureState, CatDetailsFeatureEvent, CatDetailsFeatureAction>() {

    override val _stateFlow = MutableStateFlow(CatDetailsState.default)
    override val _actionFlow = MutableSharedFlow<CatDetailsAction>()

    override val feature = CatDetailsFeature(repository, viewModelScope)

    init {
        collectFeatureFlows()
    }

    override fun viewStateToFeatureState(state: CatDetailsState): CatDetailsFeatureState {
        return CatDetailsFeatureState(
            catModel = state.catModel
        )
    }

    override fun featureStateToViewState(state: CatDetailsFeatureState): CatDetailsState {
        return CatDetailsState(
            catModel = state.catModel
        )
    }

    override fun viewEventToFeatureEvent(event: CatDetailsEvent): CatDetailsFeatureEvent? {
        return when(event) {
            is CatDetailsEvent.SetIsLiked -> CatDetailsFeatureEvent.SetIsLiked(stateFlow.value.catModel)
        }
    }

    override fun featureActionToViewAction(action: CatDetailsFeatureAction): CatDetailsAction? {
        return null
    }
}