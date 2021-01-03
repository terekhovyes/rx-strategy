package me.alexeyterekhov.rxstrategy.rxjava2

import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface ValueTransformer<T, R> {
    fun forFlowable(): FlowableTransformer<T, R>
    fun forObservable(): ObservableTransformer<T, R>
    fun forSingle(): SingleTransformer<T, R>
    fun forMaybe(): MaybeTransformer<T, R>
}