package com.desager.catslist.mvi.domain

fun interface DelayedEvent<E : FeatureEvent> {
    fun onEvent(event: E)
}