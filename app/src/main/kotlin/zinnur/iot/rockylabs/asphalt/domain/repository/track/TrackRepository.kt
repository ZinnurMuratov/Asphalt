package zinnur.iot.rockylabs.asphalt.domain.repository.track

import io.reactivex.Observable
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track

/**
 * Created by Zinnur on 07.06.17.
 */
interface TrackRepository{
    fun create(track: Track): Observable<Track>

    fun putTrackData(pathData: PathData) : Observable<Boolean>

    fun getAll() : Observable<List<Track>>

    fun get(id: String) : Observable<List<PathData>>
}