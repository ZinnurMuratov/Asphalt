package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 10.04.17.
 */
interface OnMapView  {

    fun putMarker(lat: Double, lng: Double, lvl: Double, axis: String)

    fun onMarkersReady()
}