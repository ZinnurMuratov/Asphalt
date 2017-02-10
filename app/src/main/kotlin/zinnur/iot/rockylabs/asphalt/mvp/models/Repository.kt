package zinnur.iot.rockylabs.asphalt.mvp.models

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by Zinnur on 12.01.17.
 */

class Repository : Serializable {

    @SerializedName("name") var name: String? = null
        internal set
    @SerializedName("full_name") var fullName: String? = null
        internal set
}