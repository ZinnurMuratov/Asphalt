package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import zinnur.iot.rockylabs.asphalt.domain.repository.pothole.PotholeRepository
import javax.inject.Inject

/**
 * Created by Zinnur on 04.06.17.
 */
class CachePotholeUseCase @Inject internal constructor(private val potholeRepository: PotholeRepository,
                                                       scheduler: Scheduler)
    : UseCase<Pothole, CachePotholeUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<Pothole> {
        return potholeRepository.save(params.pothole)

    }


    class Params private constructor(val pothole: Pothole) {
        companion object {
            fun withCoords(z: String?,y: String?,x: String?,g:String?,time: String ,latLng: String): Params {
                return Params(Pothole(z,y,x,g,latLng,time))
            }
        }
    }
}