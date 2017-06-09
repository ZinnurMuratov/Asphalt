package zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UseCase
import zinnur.iot.rockylabs.asphalt.domain.repository.track.TrackRepository
import java.util.*
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */
class CreateTrackUseCase @Inject internal constructor(private val potholeRepository: TrackRepository,
                                                       scheduler: Scheduler)
    : UseCase<Track, CreateTrackUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<Track> {
        return potholeRepository.create(params.track)

    }


    class Params private constructor(val track: Track) {
        companion object {
            fun withId(id: String?, time: String?): Params {
                var idNotNull = System.currentTimeMillis().toString() + Random().nextInt().toString()
                id?.let { idNotNull = it }
                return Params(Track(idNotNull,time))
            }
        }
    }
}