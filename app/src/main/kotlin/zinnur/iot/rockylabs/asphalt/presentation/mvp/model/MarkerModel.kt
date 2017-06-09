package zinnur.iot.rockylabs.asphalt.presentation.mvp.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Tile
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by Zinnur on 08.06.17.
 */
class MarkerModel : ClusterItem {
    private val mPosition: LatLng
    private var mTitle: String? = null
    private var mSnippet: String? = null

    constructor(lat: Double, lng: Double) {
        mPosition = LatLng(lat, lng)
        mTitle = null
        mSnippet = null
    }

    constructor(lat: Double, lng: Double, title: String, snippet: String) {
        mPosition = LatLng(lat, lng)
        mTitle = title
        mSnippet = snippet
    }

    override fun getPosition(): LatLng {
        return mPosition
    }

    override fun getTitle(): String? {
        return mTitle
    }

    override fun getSnippet(): String? {
        return mSnippet
    }

    fun setTitle(title: String) {
        mTitle = title
    }

    fun setSnippet(snippet: String) {
        mSnippet = snippet
    }
}