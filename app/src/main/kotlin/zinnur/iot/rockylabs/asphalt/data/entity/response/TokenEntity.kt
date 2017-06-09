package zinnur.iot.rockylabs.asphalt.data.entity.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 04.04.17.
 */

class TokenEntity(
        @com.google.gson.annotations.SerializedName("accessToken") val accessToken: String,
        @com.google.gson.annotations.SerializedName("refreshToken") val refreshToken: String)
