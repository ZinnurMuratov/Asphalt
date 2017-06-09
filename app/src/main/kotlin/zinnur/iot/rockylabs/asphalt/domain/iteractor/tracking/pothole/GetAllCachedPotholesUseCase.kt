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
class GetAllCachedPotholesUseCase @Inject internal constructor(private val potholeRepository: PotholeRepository,
                                                               scheduler: Scheduler)
    : UseCase<Pothole, GetAllCachedPotholesUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<Pothole> {
        return potholeRepository
                .getAllCachedPotholes()
                .flatMapIterable { it }

    }


    class Params private constructor() {
        companion object {
            fun with(): Params {
                return Params()
            }
        }
    }
}