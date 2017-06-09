package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.AbsResponseEntity
import zinnur.iot.rockylabs.asphalt.data.entity.request.CreateHoleRequest
import zinnur.iot.rockylabs.asphalt.data.service.TrackingService
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import javax.inject.Inject

/**
 * Created by Zinnur on 09.04.17.
 */
class CreateHoleUseCase @Inject
internal constructor(private val trackingService: TrackingService, scheduler: Scheduler)
    : UseCase<AbsResponseEntity, CreateHoleUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<AbsResponseEntity> {
        return trackingService
                .create(CreateHoleRequest(params.lat, params.lng, params.level, params.axis))

    }


    class Params private constructor(val lat: Double, val lng: Double, val level: Double, val axis: String) {
        companion object {

            fun withCoords(lat: Double, lng: Double, level: Double, axis: String): Params {
                return Params(lat, lng, level, axis)
            }
        }
    }
}
