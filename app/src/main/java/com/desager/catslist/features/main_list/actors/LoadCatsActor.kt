package com.desager.catslist.features.main_list.actors

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.main_list.event.MainListFeatureEvent
import com.desager.catslist.mvi.domain.Actor
import com.desager.catslist.mvi.domain.DelayedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadCatsActor(
    private val repository: CatsRepository,
    private val coroutineScope: CoroutineScope,
    private val delayedEvent: DelayedEvent<MainListFeatureEvent>
) : Actor<MainListFeatureEvent> {

    override suspend fun process(event: MainListFeatureEvent): MainListFeatureEvent? {
        return when(event) {
            is MainListFeatureEvent.LoadCats -> {
                var isLoading = true
                coroutineScope.launch {
                    val cats = repository.loadCats(event.limit)
                    isLoading = false
                    delayedEvent.onEvent(MainListFeatureEvent.ReturnCats(cats))
                }

                if (isLoading) {
                    MainListFeatureEvent.Loading
                } else {
                    null
                }
            }
            else -> null
        }
    }
}