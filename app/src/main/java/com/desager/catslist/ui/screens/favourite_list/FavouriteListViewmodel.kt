package com.desager.catslist.ui.screens.favourite_list

import androidx.lifecycle.viewModelScope
import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.favourite_list.FavouriteListFeature
import com.desager.catslist.features.favourite_list.action.FavouriteListFeatureAction
import com.desager.catslist.features.favourite_list.event.FavouriteListFeatureEvent
import com.desager.catslist.features.favourite_list.state.FavouriteListFeatureState
import com.desager.catslist.mvi.view.MviViewModel
import com.desager.catslist.ui.screens.favourite_list.action.FavouriteListAction
import com.desager.catslist.ui.screens.favourite_list.event.FavouriteListEvent
import com.desager.catslist.ui.screens.favourite_list.state.FavouriteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteListViewmodel @Inject constructor(
    repository: CatsRepository,
) : MviViewModel<FavouriteListState, FavouriteListEvent, FavouriteListAction, FavouriteListFeatureState, FavouriteListFeatureEvent, FavouriteListFeatureAction>() {

    override val _stateFlow = MutableStateFlow(FavouriteListState.default)
    override val _actionFlow = MutableSharedFlow<FavouriteListAction>()

    override val feature = FavouriteListFeature(repository, viewModelScope)

    private var collectFlowJob: Job? = null

    init {
        collectFeatureFlows()
        handleEvent(FavouriteListEvent.GetCatsFlow)
    }

    override fun viewStateToFeatureState(state: FavouriteListState): FavouriteListFeatureState {
        return FavouriteListFeatureState(
            cats = state.cats
        )
    }

    override fun featureStateToViewState(state: FavouriteListFeatureState): FavouriteListState {
        return FavouriteListState(
            cats = state.cats
        )
    }

    override fun viewEventToFeatureEvent(event: FavouriteListEvent): FavouriteListFeatureEvent? {
        return when(event) {
            is FavouriteListEvent.GetCatsFlow -> FavouriteListFeatureEvent.GetCatsFlow
            is FavouriteListEvent.NavigateToDetails -> FavouriteListFeatureEvent.NavigateToDetails(event.model)
            else -> null
        }
    }

    override fun featureActionToViewAction(action: FavouriteListFeatureAction): FavouriteListAction? {
        return when(action) {
            is FavouriteListFeatureAction.NavigateToDetails -> FavouriteListAction.NavigateToDetails(action.model)
            is FavouriteListFeatureAction.CollectFlow -> {
                collectFlowJob?.cancel()
                collectFlowJob = viewModelScope.launch {
                    action.flow
                        .collect { list ->
                            handleState(stateFlow.value.copy(cats = list))
                        }
                }

                null
            }
        }
    }
}