package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bluelinelabs.conductor.RestoreViewOnCreateController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.*
import com.sdoward.rxgooglemap.MapObservableProvider
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.OnMemoryLowCallback
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.TrackLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.TrackPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackView
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.ifParamsNotNull
import zinnur.iot.rockylabs.asphalt.presentation.navigator


/**
 * Created by Zinnur on 07.06.17.
 */
class TrackController : RestoreViewOnCreateController, OnMemoryLowCallback, TrackView {

    constructor() : super()
    constructor(bundle: Bundle) : super(bundle)
    constructor(track: Track?) {
        this.track = track
    }
    private var track: Track ?= null
    lateinit var container: FrameLayout
    var mapView: MapView? = null
    var gMap: GoogleMap? = null
    private lateinit var mapProvider: MapObservableProvider
    private lateinit var presenter: TrackPresenter
    private lateinit var activityCallback: MainView
    private var points :ArrayList<LatLng>? = arrayListOf()
    private var markers : HashMap<Marker, Pothole> = hashMapOf()
    private val viewBinder = TrackLayout()
    val ZOOM_MAXIMUM_LEVEL = 12f
    val KAZAN_DEFAULT_LAT_LNG = LatLng(55.784747, 49.1214908)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        val view = viewBinder.bind(this)
        mapView?.onCreate(savedViewState)
        activityCallback = activity as MainView
        activityCallback.lockDrawer(true)
        activityCallback.setHomeEnabled(true)
        activityCallback.changeTitle("Road")
        presenter = daggerComponent.trackPresenter()
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
                    track?.id?.let { presenter.getPath(it) }
                }
        onClickMarker()
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

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onMemoryLow() {
        mapView?.onLowMemory()
    }

    fun onClickMarker(){
        mapProvider.markerClickObservable.subscribe {
            retainViewMode = RetainViewMode.RETAIN_DETACH
            Log.d("marker clicked", " " + markers[it]?.created )
            navigator.showPothole(markers[it])
        }
    }

    override fun showError() {
    }

    override fun setData(data: PathData) {
        ifParamsNotNull(data.lat, data.lng)?.let {
            Log.d("set data", " - " + it[0])
            val position = LatLng(it[0], it[1])
            points?.add(position)
        }
        data.pothole?.let {
            Log.d("pothole2", " - " + it.created)
            ifParamsNotNull(data.lat, data.lng)?.let{ putMarkers(it[0], it[1], data.pothole!!) }
        }
    }

    fun putMarkers(lat: Double, lng: Double, pothole: Pothole) {
        pothole?.let {
            val marker = gMap?.addMarker(MarkerOptions().position(LatLng(lat,lng)))
            marker?.let { markers.put(marker,pothole) }
        }
    }

    override fun draw() {
        gMap?.let {
            val options = PolylineOptions().color(Color.BLACK).addAll(points)
            val polyline = it.addPolyline(options)
            moveCameraToBounds()
        }
    }

    fun moveCameraToBounds(){
        val builder = LatLngBounds.Builder()
        var i = 0
        points?.forEach { builder.include(LatLng(it.latitude, it.longitude));i++}
        if (i>0) { gMap?.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 25))}
    }


}