package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.hardware.SensorManager
import android.location.Location
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingView
import javax.inject.Inject
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import com.google.android.gms.location.LocationRequest
import com.patloew.rxlocation.FusedLocation
import io.reactivex.disposables.Disposables
import io.reactivex.observers.DisposableObserver
import org.jetbrains.annotations.Nullable
import rx.Observable
import rx.Subscriber
import rx.subscriptions.Subscriptions
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.data.entity.response.AbsResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole.CachePotholeUseCase
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole.CreateHoleUseCase
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.path.CreateTrackUseCase
import zinnur.iot.rockylabs.asphalt.domain.iteractor.tracking.pothole.CreateTrackingPointUseCase
import zinnur.iot.rockylabs.asphalt.presentation.utils.GeoUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Zinnur on 28.02.17.
 */
class TrackingPresenter @Inject constructor(@Nullable var sensor: Observable<ReactiveSensorEvent>,
                                            @Nullable var locSensor: FusedLocation,
                                            var locationRequest: LocationRequest,
                                            val authPreferences: AuthPreferences,
                                            var createTrackUseCase: CreateTrackUseCase,
                                            var createTrackingPointUseCase: CreateTrackingPointUseCase,
                                            var cachePotholeUseCase: CachePotholeUseCase,
                                            var createHoleUseCase: CreateHoleUseCase) : MvpBasePresenter<TrackingView>(){

    private var sensorSubsciption = Subscriptions.empty()
    private var locationSubscription = Disposables.empty()
    private var lvl: Float = 2.0f
    private var lastLatLng: Location? = null
    private var isTrakingPothole: Boolean = false
    private var zList: MutableList<Float> ?= arrayListOf()
    private var xList: MutableList<Float> ?= arrayListOf()
    private var yList: MutableList<Float> ?= arrayListOf()
    private var gList: MutableList<Double> ?= arrayListOf()
    private var trackingId: String?= null
    var startTime: Long = System.currentTimeMillis()


    fun startTraking(){
        startLocationSensor()
        startHoleSensor()
        createRecordWithTrackingId()
    }

    fun startHoleSensor(){
        var time = System.currentTimeMillis()
        sensorSubsciption = sensor.subscribe(object : Subscriber<ReactiveSensorEvent>() {
            override fun onCompleted() {}
            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
                startHoleSensor()
            }
            override fun onNext(event: ReactiveSensorEvent) {
                val gX = event.sensorEvent.values[0] / SensorManager.GRAVITY_EARTH
                val gY = event.sensorEvent.values[1] / SensorManager.GRAVITY_EARTH
                val gZ = event.sensorEvent.values[2] / SensorManager.GRAVITY_EARTH
                val gForce = Math.sqrt((gX * gX).toDouble() + (gY * gY).toDouble() + (gZ * gZ).toDouble())
                Log.d("gForce is", " " + gForce )
                view?.updateGraph(gForce)
                rangeAlgorithm(gX,gY,gZ,gForce)
            }
        })
    }

    fun rangeAlgorithm(gX: Float, gY: Float, gZ: Float, gForce: Double){
        if (gForce > lvl || isTrakingPothole){
            if (!isTrakingPothole) {
                startTime = System.currentTimeMillis()
                isTrakingPothole = true
                view?.updateBackgroundColor()
                xList?.clear();yList?.clear(); zList?.clear(); gList?.clear()
            } else if (System.currentTimeMillis() - startTime >= 1500){
                isTrakingPothole = false
                var zS = ""; var xS = ""; var yS = ""; var gS = ""
                doAsync {
                    zList?.forEach {  zS += it.toString() + ";" }
                    xList?.forEach {  xS += it.toString() + ";" }
                    yList?.forEach {  yS += it.toString() + ";" }
                    gList?.forEach {  gS += it.toString() + ";" }
                    uiThread {
                        val pothole = Pothole(zS,yS,xS,gS,lastLatLng?.latitude.toString() + ","+ lastLatLng?.longitude.toString(), startTime.toString())
                        createTrackingPointUseCase.execute(CacheObserver(), CreateTrackingPointUseCase.Params
                                .with(pothole, trackingId,lastLatLng?.latitude, lastLatLng?.longitude ))
                    }
                }


            }
            if (isTrakingPothole){
                zList?.add(gZ)
                xList?.add(gX)
                yList?.add(gY)
                gList?.add(gForce)
            }

        }
    }



    fun startLocationSensor(){
        try {
            locationSubscription = locSensor
                    .updates(locationRequest)
                    .subscribe{ workWithLocation(it)}
        } catch (e: SecurityException){
            e.printStackTrace()
            stopTracking()
            view?.showLocationNotAvailable()
        }
    }

    fun workWithLocation(location: Location){
        lastLatLng?.let {
            async(UI) {
                val isMoreOneMeter: Deferred<Boolean> = bg {
                    GeoUtils.distanceMoreThanOneMetr(it,location)
                }
                if (isMoreOneMeter.await() && !isTrakingPothole) {
                    createTrackingPointUseCase.execute(CacheObserver(), CreateTrackingPointUseCase.Params
                            .with(null, trackingId,it.latitude, it.longitude ))
                } else { }
            }
        }
        lastLatLng = location
    }

    fun createRecordWithTrackingId(){
        async(UI){
            val id: Deferred<String> = bg { generateTrackingId() }
            val time: Deferred<String> = bg { generateStartTime()}
            trackingId = id.await()
            createTrackUseCase.execute(CacheTrackObserver(), CreateTrackUseCase.Params.withId(id.await(), time.await()))
        }

    }


    fun onHole(axis: String, lvl: Double){

        lastLatLng?.hasSpeed()?.let {}

        view?.updateBackgroundColor()
        lastLatLng?.let {
            var lat = it.latitude
            var lng = it.longitude
            createHoleUseCase.execute(TrackingObserver(), CreateHoleUseCase.Params.withCoords(lat, lng, lvl, axis))
        }
    }

    fun generateTrackingId(): String{
        var id = System.currentTimeMillis().toString()
        val user = authPreferences.userAuthCredentials
        user?.email?.let { id += it }
        return id
    }

    fun generateStartTime(): String{
        val df = SimpleDateFormat("dd.MM.yyyy, HH:mm:ss")
        val date = df.format(Calendar.getInstance().time)
        return date.toString()
    }



    fun stopTracking(){
        if (!sensorSubsciption?.isUnsubscribed!!){
            sensorSubsciption?.unsubscribe()
        }

        if (!locationSubscription?.isDisposed!!){
            locationSubscription?.dispose()
        }
        createHoleUseCase.dispose()
    }

    fun setSensetiveLvl(lvl: Float){
        this.lvl = lvl
    }

    private inner class TrackingObserver : DisposableObserver<AbsResponseEntity>() {
        override fun onComplete() {}
        override fun onError(e: Throwable) = e.printStackTrace()
        override fun onNext(res: AbsResponseEntity) { Log.d("result ->", " " + res.status) }
    }

    private inner class CacheObserver : DisposableObserver<Boolean>(){
        override fun onComplete() { Log.d("pothole"," complete") }
        override fun onError(e: Throwable) = e.printStackTrace()
        override fun onNext(isCreated: Boolean) {}
    }

    private inner class CacheTrackObserver : DisposableObserver<Track>(){
        override fun onComplete() { }
        override fun onError(e: Throwable) = e.printStackTrace()
        override fun onNext(track: Track) {  Log.d("track","id " + track.id)}
    }
}