package zinnur.iot.rockylabs.asphalt.data.entity.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 11.04.17.
 */
class FeedEntity(
        @com.google.gson.annotations.SerializedName("_id") val id: String,
        @com.google.gson.annotations.SerializedName("lat") val lat: Double,
        @com.google.gson.annotations.SerializedName("lng") val lng: Double,
        @com.google.gson.annotations.SerializedName("city") var city: String,
        @com.google.gson.annotations.SerializedName("url") var imageUrl: String,
        @com.google.gson.annotations.SerializedName("createdAt") var createdAt: String)