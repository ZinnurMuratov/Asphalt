package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import zinnur.iot.rockylabs.asphalt.domain.repository.track.TrackRepository
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */
class GetAllCachedTracksUseCase @Inject internal constructor(private val trackRepository: TrackRepository,
                                                               scheduler: Scheduler)
    : UseCase<Track, GetAllCachedTracksUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<Track> {
        return trackRepository
                .getAll()
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