package zinnur.iot.rockylabs.asphalt.data.entity.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 09.04.17.
 */

class CreateHoleRequest(@com.google.gson.annotations.SerializedName("lat") var lat: Double,
                        @com.google.gson.annotations.SerializedName("lng") var lng: Double,
                        @com.google.gson.annotations.SerializedName("level") var level: Double,
                        @com.google.gson.annotations.SerializedName("axis") var axis: String)

