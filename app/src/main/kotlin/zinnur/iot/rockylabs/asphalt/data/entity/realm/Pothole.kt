package zinnur.iot.rockylabs.asphalt.data.entity.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass


/**
 * Created by Zinnur on 04.06.17.
 */
@RealmClass
open class Pothole(var z: String? = null,
                   var y: String? = null,
                   var x: String? = null,
                   var g: String? = null,
                   var latlng: String? = null,
                   var created: String? = null) : RealmObject()

