package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 11.04.17.
 */
class FeedEntity(
        @SerializedName("_id") val id: String,
        @SerializedName("lat") val lat: Double,
        @SerializedName("lng") val lng: Double,
        @SerializedName("city") var city: String,
        @SerializedName("url") var imageUrl: String,
        @SerializedName("createdAt") var createdAt: String)