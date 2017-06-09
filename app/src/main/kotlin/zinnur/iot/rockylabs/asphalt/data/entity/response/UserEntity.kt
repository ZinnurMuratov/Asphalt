package zinnur.iot.rockylabs.asphalt.data.entity.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 06.04.17.
 */
class UserEntity(
        @SerializedName("email") val email: String,
        @SerializedName("name")  val name: String)
