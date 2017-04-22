package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 09.04.17.
 */

class GetHoleResponseEntity(status: String, @SerializedName("data") val holesEntity: List<HolesEntity>) : AbsResponseEntity(status)