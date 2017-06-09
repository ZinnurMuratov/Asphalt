package zinnur.iot.rockylabs.asphalt.data.entity.response

/**
 * Created by Zinnur on 06.04.17.
 */

class UserResponseEntity(status: String, @com.google.gson.annotations.SerializedName("result") val user: UserEntity) : AbsResponseEntity(status)

