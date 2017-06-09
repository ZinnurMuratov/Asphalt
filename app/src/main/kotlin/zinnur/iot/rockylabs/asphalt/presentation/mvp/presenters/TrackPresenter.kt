package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path.GetCachedPathUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.PotholeView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackView
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */
class TrackPresenter @Inject constructor(var getCachedPathUseCase: GetCachedPathUseCase) : MvpBasePresenter<TrackView>() {

    fun getPath(id: String){
        getCachedPathUseCase.execute(CacheTrackObserver(), GetCachedPathUseCase.Params.id(id))
    }

    private inner class CacheTrackObserver : DisposableObserver<PathData>(){
        override fun onComplete() { view?.draw()}
        override fun onError(e: Throwable) = e.printStackTrace()
        override fun onNext(track: PathData) { view?.setData(track)}
    }

}