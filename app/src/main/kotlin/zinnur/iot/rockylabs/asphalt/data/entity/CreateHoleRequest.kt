package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 09.04.17.
 */

class CreateHoleRequest(@SerializedName("lat") var lat: Double,
                        @SerializedName("lng") var lng: Double,
                        @SerializedName("level") var level: Double,
                        @SerializedName("axis") var axis: String)

