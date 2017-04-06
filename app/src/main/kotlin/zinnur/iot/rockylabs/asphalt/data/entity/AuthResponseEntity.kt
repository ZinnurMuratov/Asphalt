package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 04.04.17.
 */

class AuthResponseEntity(status: String, @SerializedName("result") val tokenEntity: TokenEntity) : AbsResponseEntity(status)
