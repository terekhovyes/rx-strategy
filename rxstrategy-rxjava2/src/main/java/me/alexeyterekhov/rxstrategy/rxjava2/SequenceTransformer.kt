package me.alexeyterekhov.rxstrategy.rxjava2

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer

interface SequenceTransformer<T, R> {
    fun forFlowable(): FlowableTransformer<T, R>
    fun forObservable(): ObservableTransformer<T, R>
}