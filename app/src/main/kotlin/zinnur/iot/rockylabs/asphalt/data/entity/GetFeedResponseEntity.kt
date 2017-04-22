package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 11.04.17.
 */
class GetFeedResponseEntity(status: String, @SerializedName("data") val feedEntity: List<FeedEntity>) : AbsResponseEntity(status)