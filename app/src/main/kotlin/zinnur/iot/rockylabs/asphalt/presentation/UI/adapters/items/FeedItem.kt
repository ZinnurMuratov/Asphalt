package zinnur.iot.rockylabs.asphalt.presentation.UI.adapters.items

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView

import com.mikepenz.fastadapter.items.AbstractItem
import com.squareup.picasso.Picasso

import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 11.04.17.
 */

class FeedItem : AbstractItem<FeedItem, FeedItem.ViewHolder>() {

    var mImageUrl: String = ""

    fun withImage(imageUrl: String): FeedItem {
        this.mImageUrl = imageUrl
        return this
    }


    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    override fun getType(): Int {
        return R.id.fastadapter_feed_item_id
    }

    override fun getLayoutRes(): Int {
        return R.layout.feed_item
    }

    override fun bindView(holder: ViewHolder, payloads: List<Any>?) {
        super.bindView(holder, payloads)

        val ctx = holder.itemView.context
        Picasso.with(ctx).load(mImageUrl).into(holder.imageView)
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.item_image_img) as ImageView

    }
}
