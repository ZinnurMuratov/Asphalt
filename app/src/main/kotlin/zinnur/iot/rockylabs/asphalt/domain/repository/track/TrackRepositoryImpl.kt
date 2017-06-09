package zinnur.iot.rockylabs.asphalt.domain.repository.track

import android.util.Log
import io.reactivex.Observable
import io.realm.Sort
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.repository.RealmController
import javax.inject.Inject

/**
 * Created by Zinnur on 07.06.17.
 */
class TrackRepositoryImpl
@Inject constructor(private val realmController: RealmController) : TrackRepository{

    override fun create(track: Track): Observable<Track> {
        return realmController.executeTransaction {
            val inserted = it.copyToRealm(track)
            return@executeTransaction it.copyFromRealm(inserted)
        }
    }

    override fun putTrackData(pathData: PathData): Observable<Boolean> {
        return realmController.executeTransaction {
            val inserted = it.copyToRealm(pathData)
            inserted.pothole?.let { Log.d("pothole", "in house") }
            return@executeTransaction it.copyFromRealm(inserted).isValid
        }
    }

    override fun getAll(): Observable<List<Track>> {
        return realmController.executeTransaction {
            val results = it.where(Track::class.java).findAllSorted("started", Sort.DESCENDING)
            return@executeTransaction it.copyFromRealm(results)
        }
    }

    override fun get(id: String): Observable<List<PathData>> {
        return realmController.executeTransaction {
            val results = it.where(PathData::class.java).equalTo("trackId", id).findAll()
            return@executeTransaction it.copyFromRealm(results)
        }
    }
}