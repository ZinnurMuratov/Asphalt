package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path.GetAllCachedTracksUseCase
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole.GetAllCachedPotholesUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingFeedView
import javax.inject.Inject

/**
 * Created by Zinnur on 04.06.17.
 */
class TrackingFeedPresenter @Inject constructor(val getAllCachedTracksUseCase: GetAllCachedTracksUseCase) : MvpBasePresenter<TrackingFeedView>() {

    init {
        getFeed()
    }
    fun getFeed() {
        getAllCachedTracksUseCase.execute(FeedObserver(), GetAllCachedTracksUseCase.Params.with())
    }




    private inner class FeedObserver : DisposableObserver<Track>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(track: Track) {
            view?.setFeed(track)

        }
    }

}

