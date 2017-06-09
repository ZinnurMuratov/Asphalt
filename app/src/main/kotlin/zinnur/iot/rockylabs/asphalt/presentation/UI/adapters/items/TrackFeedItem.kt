package zinnur.iot.rockylabs.asphalt.presentation.UI.adapters.items

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.mikepenz.fastadapter.items.AbstractItem
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 04.06.17.
 */
class TrackFeedItem : AbstractItem<TrackFeedItem, TrackFeedItem.ViewHolder>() {

    private var track: Track? = null

    fun setData(track:  Track?): TrackFeedItem {
        this.track = track
        return this
    }

    fun getTrack(): Track? = track


    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun getType(): Int {
        return R.id.fastadapter_feed_item_id
    }

    override fun getLayoutRes(): Int {
        return R.layout.track_feed_item
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>?) {
        super.bindView(holder, payloads)
        holder.locationTV.text = "created: " + track?.started
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var locationTV: TextView = view.findViewById(R.id.item_text_location) as TextView

    }
}
