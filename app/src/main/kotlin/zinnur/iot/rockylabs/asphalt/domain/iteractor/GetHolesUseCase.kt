package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.GetHoleResponseEntity
import zinnur.iot.rockylabs.asphalt.data.service.TrackingService
import javax.inject.Inject

/**
 * Created by Zinnur on 10.04.17.
 */
class GetHolesUseCase @Inject
internal constructor(private val trackingService: TrackingService, scheduler: Scheduler)
    : UseCase<GetHoleResponseEntity, GetHolesUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<GetHoleResponseEntity> {
        return trackingService
                .get(params.city)

    }


    class Params private constructor(val city: String) {
        companion object {

            fun ofCity(city: String): Params {
                return Params(city)
            }
        }
    }
}
