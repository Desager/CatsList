package com.desager.catslist.features.favourite_list.actors

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.favourite_list.event.FavouriteListFeatureEvent
import com.desager.catslist.mvi.domain.Actor
import com.desager.catslist.mvi.domain.DelayedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GetCatsFlowActor(
    private val repository: CatsRepository,
    private val coroutineScope: CoroutineScope,
    private val delayedEvent: DelayedEvent<FavouriteListFeatureEvent>
) : Actor<FavouriteListFeatureEvent> {

    override suspend fun process(event: FavouriteListFeatureEvent): FavouriteListFeatureEvent? {
        return when(event) {
            FavouriteListFeatureEvent.GetCatsFlow -> {
                coroutineScope.launch {
                    val flow = repository.getFavouriteCats()
                    delayedEvent.onEvent(FavouriteListFeatureEvent.ReturnCatsFlow(flow))
                }

                null
            }
            else -> null
        }
    }
}