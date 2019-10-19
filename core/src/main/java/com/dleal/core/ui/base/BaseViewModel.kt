package com.dleal.core.ui.base

import androidx.lifecycle.ViewModel
import com.dleal.core.utils.CLogger
import com.dleal.core.utils.RxTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(
    open val rxTransformer: RxTransformer? = null
) : ViewModel(), CLogger {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

    fun addDisposable(disposable: Disposable) {
        if (compositeDisposable.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable.add(disposable)
    }
}