package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RestoreViewOnCreateController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.sdoward.rxgooglemap.MapObservableProvider
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.OnMemoryLowCallback
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.MapLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.MapPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.OnMapView
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 16.02.17.
 */

class OnMapController : RestoreViewOnCreateController(), OnMapView, OnMemoryLowCallback {

    private val viewBinder = MapLayout()
    var mapView: MapView? = null
    var gMap: GoogleMap? = null
    private lateinit var mapProvider: MapObservableProvider
    private lateinit var presenter: MapPresenter

    val ZOOM_MAXIMUM_LEVEL = 8f
    val KAZAN_DEFAULT_LAT_LNG = LatLng(55.784747, 49.1214908)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        val view = viewBinder.bind(this)
        mapView?.onCreate(savedViewState)
        presenter = daggerComponent.mapPresenter()
        presenter.attachView(this)
        MapsInitializer.initialize(applicationContext)
        mapProvider = MapObservableProvider(mapView)
        mapProvider.mapReadyObservable
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{ googleMap ->
                    gMap = googleMap
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KAZAN_DEFAULT_LAT_LNG, ZOOM_MAXIMUM_LEVEL))
                    googleMap.setMinZoomPreference(ZOOM_MAXIMUM_LEVEL)
                    presenter.getHoles()

        }
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        mapView?.onResume()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        mapView?.onPause()
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onMemoryLow() {
        mapView?.onLowMemory()
    }

    override fun putMarkers(lat: Double, lng: Double, lvl: Double, axis: String) {
        var color: Int
        if (axis.equals('x')) {
            color = ContextCompat.getColor(applicationContext, R.color.greenOpacity)
        } else if (axis.equals('y')){
            color = ContextCompat.getColor(applicationContext, R.color.blueOpacity)
        } else {
            color = ContextCompat.getColor(applicationContext, R.color.redOpacity)
        }

        val marker = gMap?.addCircle(CircleOptions()
                .center(LatLng(lat,lng))
                .radius(lvl * 10)
                .fillColor(color)
                .strokeWidth(0f))

    }

}