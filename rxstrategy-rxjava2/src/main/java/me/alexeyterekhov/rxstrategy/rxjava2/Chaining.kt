package me.alexeyterekhov.rxstrategy.rxjava2

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

fun <T, R> AnyTransformer.andThen(next: SequenceTransformer<T, R>): SequenceTransformer<T, R> =
    AnyThenSequence(this, next)

fun <T, R> AnyTransformer.andThen(next: ValueTransformer<T, R>): ValueTransformer<T, R> =
    AnyThenValue(this, next)

fun AnyTransformer.andThen(next: AnyTransformer): AnyTransformer =
    AnyThenAny(this, next)

fun <T, R, X> ValueTransformer<T, R>.andThen(next: SequenceTransformer<R, X>): SequenceTransformer<T, X> =
    ValueThenSequence(this, next)

fun <T, R, X> ValueTransformer<T, R>.andThen(next: ValueTransformer<R, X>): ValueTransformer<T, X> =
    ValueThenValue(this, next)

fun <T, R> ValueTransformer<T, R>.andThen(next: AnyTransformer): ValueTransformer<T, R> =
    ValueThenAny(this, next)

fun <T, R, X> SequenceTransformer<T, R>.andThen(next: SequenceTransformer<R, X>): SequenceTransformer<T, X> =
    SequenceThenSequence(this, next)

fun <T, R, X> SequenceTransformer<T, R>.andThen(next: ValueTransformer<R, X>): SequenceTransformer<T, X> =
    SequenceThenValue(this, next)

fun <T, R> SequenceTransformer<T, R>.andThen(next: AnyTransformer): SequenceTransformer<T, R> =
    SequenceThenAny(this, next)

private class SequenceThenSequence<T, R, X>(
    private val first: SequenceTransformer<T, R>,
    private val second: SequenceTransformer<R, X>
) : SequenceTransformer<T, X> {

    override fun forFlowable() = FlowableTransformer<T, X> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, X> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }
}

private class SequenceThenValue<T, R, X>(
    private val first: SequenceTransformer<T, R>,
    private val second: ValueTransformer<R, X>
) : SequenceTransformer<T, X> {

    override fun forFlowable() = FlowableTransformer<T, X> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, X> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }
}

private class SequenceThenAny<T, R>(
    private val first: SequenceTransformer<T, R>,
    private val second: AnyTransformer
) : SequenceTransformer<T, R> {

    override fun forFlowable() = FlowableTransformer<T, R> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, R> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }
}

private class ValueThenSequence<T, R, X>(
    private val first: ValueTransformer<T, R>,
    private val second: SequenceTransformer<R, X>
) : SequenceTransformer<T, X> {

    override fun forFlowable() = FlowableTransformer<T, X> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, X> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }
}

private class ValueThenValue<T, R, X>(
    private val first: ValueTransformer<T, R>,
    private val second: ValueTransformer<R, X>
) : ValueTransformer<T, X> {

    override fun forFlowable() = FlowableTransformer<T, X> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, X> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }

    override fun forSingle() = SingleTransformer<T, X> { source ->
        source
            .compose(first.forSingle())
            .compose(second.forSingle())
    }

    override fun forMaybe() = MaybeTransformer<T, X> { source ->
        source
            .compose(first.forMaybe())
            .compose(second.forMaybe())
    }
}

private class ValueThenAny<T, R>(
    private val first: ValueTransformer<T, R>,
    private val second: AnyTransformer
) : ValueTransformer<T, R> {

    override fun forFlowable() = FlowableTransformer<T, R> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, R> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }

    override fun forSingle() = SingleTransformer<T, R> { source ->
        source
            .compose(first.forSingle())
            .compose(second.forSingle())
    }

    override fun forMaybe() = MaybeTransformer<T, R> { source ->
        source
            .compose(first.forMaybe())
            .compose(second.forMaybe())
    }
}

private class AnyThenSequence<T, R>(
    private val first: AnyTransformer,
    private val second: SequenceTransformer<T, R>
) : SequenceTransformer<T, R> {

    override fun forFlowable() = FlowableTransformer<T, R> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, R> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }
}

private class AnyThenValue<T, R>(
    private val first: AnyTransformer,
    private val second: ValueTransformer<T, R>
) : ValueTransformer<T, R> {

    override fun forFlowable() = FlowableTransformer<T, R> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun forObservable() = ObservableTransformer<T, R> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }

    override fun forSingle() = SingleTransformer<T, R> { source ->
        source
            .compose(first.forSingle())
            .compose(second.forSingle())
    }

    override fun forMaybe() = MaybeTransformer<T, R> { source ->
        source
            .compose(first.forMaybe())
            .compose(second.forMaybe())
    }
}

private class AnyThenAny(
    private val first: AnyTransformer,
    private val second: AnyTransformer
) : AnyTransformer {

    override fun <T> forFlowable() = FlowableTransformer<T, T> { source ->
        source
            .compose(first.forFlowable())
            .compose(second.forFlowable())
    }

    override fun <T> forObservable() = ObservableTransformer<T, T> { source ->
        source
            .compose(first.forObservable())
            .compose(second.forObservable())
    }

    override fun <T> forSingle() = SingleTransformer<T, T> { source ->
        source
            .compose(first.forSingle())
            .compose(second.forSingle())
    }

    override fun <T> forMaybe() = MaybeTransformer<T, T> { source ->
        source
            .compose(first.forMaybe())
            .compose(second.forMaybe())
    }

    override fun forCompletable() = CompletableTransformer { source ->
        source
            .compose(first.forCompletable())
            .compose(second.forCompletable())
    }
}
