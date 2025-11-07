package com.desager.catslist.ui.screens.main_list

import androidx.lifecycle.viewModelScope
import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.main_list.MainListFeature
import com.desager.catslist.features.main_list.action.MainListFeatureAction
import com.desager.catslist.features.main_list.event.MainListFeatureEvent
import com.desager.catslist.features.main_list.state.MainListFeatureState
import com.desager.catslist.mvi.view.MviViewModel
import com.desager.catslist.ui.screens.main_list.action.MainListAction
import com.desager.catslist.ui.screens.main_list.event.MainListEvent
import com.desager.catslist.ui.screens.main_list.state.MainListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainListViewmodel @Inject constructor(
    repository: CatsRepository,
) : MviViewModel<MainListState, MainListEvent, MainListAction,
        MainListFeatureState, MainListFeatureEvent, MainListFeatureAction>() {

    override val _stateFlow = MutableStateFlow(MainListState.default)
    override val _actionFlow = MutableSharedFlow<MainListAction>()

    override val feature = MainListFeature(repository, viewModelScope)

    init {
        collectFeatureFlows()
    }

    override fun viewStateToFeatureState(state: MainListState): MainListFeatureState {
        return feature.stateFlow.value
    }

    override fun featureStateToViewState(state: MainListFeatureState): MainListState {
        return MainListState(
            isLoading = state.isLoading,
            cats = state.cats
        )
    }

    override fun viewEventToFeatureEvent(event: MainListEvent): MainListFeatureEvent? {
        return when(event) {
            is MainListEvent.LoadCats -> MainListFeatureEvent.LoadCats(event.limit)
            is MainListEvent.NavigateToDetails -> MainListFeatureEvent.NavigateToDetails(event.model)
        }
    }

    override fun featureActionToViewAction(action: MainListFeatureAction): MainListAction? {
        return when(action) {
            is MainListFeatureAction.NavigateToDetails -> MainListAction.NavigateToDetails(action.model)
        }
    }
}