package zinnur.iot.rockylabs.asphalt.data.entity.response


import com.google.gson.annotations.SerializedName

class HolesEntity(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lng") val lng: Double,
        @SerializedName("level") var level: Double,
        @SerializedName("axis") var axis: String)

