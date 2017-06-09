package zinnur.iot.rockylabs.asphalt.data.entity.response

/**
 * Created by Zinnur on 11.04.17.
 */
class GetFeedResponseEntity(status: String, @com.google.gson.annotations.SerializedName("data") val feedEntity: List<FeedEntity>) : AbsResponseEntity(status)