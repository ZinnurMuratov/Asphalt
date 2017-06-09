package zinnur.iot.rockylabs.asphalt.data.entity.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Zinnur on 07.06.17.
 */
open class PathData(var trackId: String? = null,
                    var lat: Double? = null,
                    var lng: Double? = null,
                    var pothole: Pothole? = null) : RealmObject()
