package zinnur.iot.rockylabs.asphalt.data.entity.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 11.04.17.
 */

class CreatePhotoHoleRequest(@SerializedName("lat") var lat: Double,
                             @SerializedName("lng") var lng: Double,
                             @SerializedName("url") var url: String)