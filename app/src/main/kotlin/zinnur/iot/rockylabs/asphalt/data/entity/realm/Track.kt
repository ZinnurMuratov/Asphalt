package zinnur.iot.rockylabs.asphalt.data.entity.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Zinnur on 07.06.17.
 */
@RealmClass
open class Track(@PrimaryKey var id: String? = null,
                 var started: String? = null,
                 var isSyncWithServer: Boolean? = false) : RealmObject()
