package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import zinnur.iot.rockylabs.asphalt.domain.mapper.PotholeFeedMapper
import zinnur.iot.rockylabs.asphalt.domain.model.TrackModel
import zinnur.iot.rockylabs.asphalt.domain.repository.track.TrackRepository
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */

class GetCachedPathUseCase @Inject internal constructor(private val trackRepository: TrackRepository,
                                                        scheduler: Scheduler)
    : UseCase<PathData, GetCachedPathUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<PathData> {
        return trackRepository
                .get(params.id)
                .flatMapIterable{ it }

    }


    class Params private constructor(val id: String) {
        companion object {
            fun id(id: String): Params {
                return Params(id)
            }
        }
    }
}