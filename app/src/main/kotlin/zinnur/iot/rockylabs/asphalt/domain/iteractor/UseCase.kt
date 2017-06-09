package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

/**
 * Created by Zinnur on 04.04.17.
 */

abstract class UseCase<T, Params> internal constructor(private val taskScheduler: Scheduler) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    internal abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(taskScheduler)
                .observeOn(AndroidSchedulers.mainThread())
        addDisposable(observable.subscribeWith(observer))
    }


    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }



}
