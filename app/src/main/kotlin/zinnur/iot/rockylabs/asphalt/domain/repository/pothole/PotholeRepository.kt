package zinnur.iot.rockylabs.asphalt.domain.repository.pothole

import io.reactivex.Observable
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole

/**
 * Created by Zinnur on 04.06.17.
 */
interface PotholeRepository{
   fun save(pothole: Pothole): Observable<Pothole>

   fun getAllCachedPotholes(): Observable<List<Pothole>>
}