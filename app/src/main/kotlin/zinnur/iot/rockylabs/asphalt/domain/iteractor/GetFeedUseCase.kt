package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.GetFeedResponseEntity
import zinnur.iot.rockylabs.asphalt.data.service.TrackingService
import javax.inject.Inject

/**
 * Created by Zinnur on 11.04.17.
 */


class GetFeedUseCase @Inject
internal constructor(private val trackingService: TrackingService, scheduler: Scheduler)
    : UseCase<GetFeedResponseEntity, GetFeedUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<GetFeedResponseEntity> {
        return trackingService
                .getFeed(params.city, params.limit, params.page)

    }


    class Params private constructor(val city: String, val limit: Long, val page: Int) {
        companion object {

            fun with(city: String, limit: Long, page: Int): Params {
                return Params(city, limit, page)
            }
        }
    }
}
