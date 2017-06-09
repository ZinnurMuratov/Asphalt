package zinnur.iot.rockylabs.asphalt.data.entity.response

/**
 * Created by Zinnur on 04.04.17.
 */

class AuthResponseEntity(status: String, @com.google.gson.annotations.SerializedName("result") val tokenEntity: TokenEntity) : AbsResponseEntity(status)
