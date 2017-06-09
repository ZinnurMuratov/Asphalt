package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import zinnur.iot.rockylabs.asphalt.domain.repository.track.TrackRepository
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */
class CreateTrackingPointUseCase @Inject internal constructor(private val trackRepository: TrackRepository,
                                                       scheduler: Scheduler)
    : UseCase<Boolean, CreateTrackingPointUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return trackRepository.putTrackData(params.data)

    }


    class Params private constructor(val data: PathData) {
        companion object {
            fun with(pothole: Pothole?,trackId: String?,lat: Double?, lng: Double?): Params {
                return Params(PathData(trackId,lat,lng,pothole))
            }
        }
    }
}