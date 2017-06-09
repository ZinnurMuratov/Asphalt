package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.AbsResponseEntity
import zinnur.iot.rockylabs.asphalt.data.entity.request.CreatePhotoHoleRequest
import zinnur.iot.rockylabs.asphalt.data.service.TrackingService
import javax.inject.Inject

/**
 * Created by Zinnur on 11.04.17.
 */
class CreatePhotoHoleUseCase @Inject
internal constructor(private val trackingService: TrackingService, scheduler: Scheduler)
    : UseCase<AbsResponseEntity, CreatePhotoHoleUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<AbsResponseEntity> {
        return trackingService
                .create(CreatePhotoHoleRequest(params.lat, params.lng, params.url))

    }


    class Params private constructor(val lat: Double, val lng: Double, val url: String) {
        companion object {

            fun withCoords(lat: Double, lng: Double, url: String): Params {
                return Params(lat, lng, url)
            }
        }
    }
}