package com.dleal.core.utils

import com.dleal.core.BuildConfig
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxTransformer {

    private val IO_SCHEDULER: Scheduler by lazy { Schedulers.io() }
    private val COMPUTATION_SCHEDULER: Scheduler by lazy { Schedulers.computation() }
    private val MAIN_THREAD_SCHEDULER: Scheduler by lazy { AndroidSchedulers.mainThread() }

    //Generic error treatment
    private fun logError(throwable: Throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace()
        }
        //Could have here reporting tool code
    }

    private fun <T> applySingleTransformers(
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ) =
        SingleTransformer<T, T> { upstream ->
            upstream.subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .doOnError { logError(it) }
        }

    private fun <T> applyFlowableTransformers(
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ) =
        FlowableTransformer<T, T> { upstream ->
            upstream.subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .doOnError { logError(it) }
        }

    //For Single
    // By the default observation scheduler is AndroidSchedulers#mainThread , but could be overridden
    // depending on situation
    fun <T> applyIoScheduler(observeScheduler: Scheduler = MAIN_THREAD_SCHEDULER):
            SingleTransformer<T, T> = apply(IO_SCHEDULER, observeScheduler)

    fun <T> applyComputationScheduler(observeScheduler: Scheduler = MAIN_THREAD_SCHEDULER):
            SingleTransformer<T, T> = apply(COMPUTATION_SCHEDULER, observeScheduler)

    private fun <T> apply(
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ):
            SingleTransformer<T, T> = applySingleTransformers(subscribeScheduler, observeScheduler)

    //For Flowable
    // By the default observation scheduler is AndroidSchedulers#mainThread , but could be overridden
    // depending on situation
    fun <T> applyIoSchedulerToFlow(observeScheduler: Scheduler = MAIN_THREAD_SCHEDULER):
            FlowableTransformer<T, T> = applyToFlow(IO_SCHEDULER, observeScheduler)

    fun <T> applyComputationSchedulerToFlow(observeScheduler: Scheduler = MAIN_THREAD_SCHEDULER):
            FlowableTransformer<T, T> = applyToFlow(COMPUTATION_SCHEDULER, observeScheduler)

    private fun <T> applyToFlow(
        subscribeScheduler: Scheduler,
        observeScheduler: Scheduler
    ):
            FlowableTransformer<T, T> =
        applyFlowableTransformers(subscribeScheduler, observeScheduler)
}