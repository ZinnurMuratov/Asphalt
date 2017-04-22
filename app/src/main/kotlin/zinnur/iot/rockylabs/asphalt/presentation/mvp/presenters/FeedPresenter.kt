package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.GetFeedResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.GetFeedUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.FeedView
import javax.inject.Inject

/**
 * Created by Zinnur on 11.04.17.
 */

class FeedPresenter @Inject constructor(var getFeedUseCase: GetFeedUseCase) : MvpBasePresenter<FeedView>() {

    init {
        getFeed(0)
    }
    fun getFeed(page: Int) {
        getFeedUseCase.execute(FeedObserver(), GetFeedUseCase.Params.with("Kazan'",10,page))
    }

    private inner class FeedObserver : DisposableObserver<GetFeedResponseEntity>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(feeds: GetFeedResponseEntity) {
            for (feed in feeds.feedEntity){
                view?.setFeed(feed)
            }
        }
    }
}