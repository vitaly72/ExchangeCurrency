package com.example.exchangecurrency.domain.usecase.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * This class is extended by SingleUseCase classes
 * to use common methods & fields
 **/
abstract class UseCase {
    var lastDisposable: Disposable? = null
    val compositeDisposable = CompositeDisposable()

    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}