package me.alexeyterekhov.rxstrategy.rxjava2

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface AnyTransformer {
    fun <T> forFlowable(): FlowableTransformer<T, T>
    fun <T> forObservable(): ObservableTransformer<T, T>
    fun <T> forSingle(): SingleTransformer<T, T>
    fun <T> forMaybe(): MaybeTransformer<T, T>
    fun forCompletable(): CompletableTransformer
}