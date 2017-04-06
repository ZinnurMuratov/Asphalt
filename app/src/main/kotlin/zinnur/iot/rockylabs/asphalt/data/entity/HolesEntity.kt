package zinnur.iot.rockylabs.asphalt.data.entity

import android.location.Location
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by Zinnur on 04.04.17.
 */

@RealmClass
open class HolesEntity : RealmObject() {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    open var id: Int = 0

    @SerializedName("latlng")
    @Expose
    open var latlng: String? = null

}