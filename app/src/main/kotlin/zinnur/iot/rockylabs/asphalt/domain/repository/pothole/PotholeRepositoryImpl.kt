package zinnur.iot.rockylabs.asphalt.domain.repository.pothole

import io.reactivex.Observable
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.domain.repository.RealmController
import javax.inject.Inject

/**
 * Created by Zinnur on 04.06.17.
 */
class PotholeRepositoryImpl
@Inject constructor(private val realmController: RealmController) : PotholeRepository{

    override fun getAllCachedPotholes(): Observable<List<Pothole>> {
        return realmController.executeTransaction {
            val results = it.where(Pothole::class.java).findAll()
            return@executeTransaction it.copyFromRealm(results)
        }
    }

    override fun save(pothole: Pothole): Observable<Pothole> {
        return realmController.executeTransaction {
            val inserted = it.copyToRealm(pothole)
            return@executeTransaction it.copyFromRealm(inserted)
        }
    }

}