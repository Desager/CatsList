package com.desager.catslist.features.cat_details.actors

import com.desager.catslist.domain.repository.CatsRepository
import com.desager.catslist.features.cat_details.event.CatDetailsFeatureEvent
import com.desager.catslist.mvi.domain.Actor
import com.desager.catslist.mvi.domain.DelayedEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SetIsLikedActor(
    private val repository: CatsRepository,
    private val coroutineScope: CoroutineScope,
    private val delayedEvent: DelayedEvent<CatDetailsFeatureEvent>
) : Actor<CatDetailsFeatureEvent> {

    override suspend fun process(event: CatDetailsFeatureEvent): CatDetailsFeatureEvent? {
        return when(event) {
            is CatDetailsFeatureEvent.SetIsLiked -> {
                coroutineScope.launch {
                    if (event.model.isLiked) {
                        repository.deleteFavouriteCat(event.model)
                        delayedEvent.onEvent(CatDetailsFeatureEvent.LikedSet)
                    } else {
                        repository.insertFavouriteCat(event.model)
                        delayedEvent.onEvent(CatDetailsFeatureEvent.LikedSet)
                    }
                }

                null
            }
            else -> null
        }
    }
}