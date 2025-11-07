package com.desager.catslist.mvi.domain

interface Reducer<S: FeatureState, E: FeatureEvent, A: FeatureAction> {

    fun reduce(event: E, state: S): Pair<S?, A?>
}