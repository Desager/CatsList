package com.desager.catslist.mvi.domain

interface Actor<E: FeatureEvent> {

    suspend fun process(event: E): E?
}