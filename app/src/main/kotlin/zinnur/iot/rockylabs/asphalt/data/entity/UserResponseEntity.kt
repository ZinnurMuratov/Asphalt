package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 06.04.17.
 */
class UserResponseEntity(status: String, @SerializedName("result") val user: UserEntity) : AbsResponseEntity(status)

