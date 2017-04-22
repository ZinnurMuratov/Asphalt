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
import zinnur.iot.rockylabs.asphalt.data.entity.AbsResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.CreateHoleUseCase


/**
 * Created by Zinnur on 28.02.17.
 */
class TrackingPresenter @Inject constructor(@Nullable var sensor: Observable<ReactiveSensorEvent>,
                                            @Nullable var locSensor: FusedLocation,
                                            var locationRequest: LocationRequest,
                                            var createHoleUseCase: CreateHoleUseCase) : MvpBasePresenter<TrackingView>(){

    private var sensorSubsciption = Subscriptions.empty()
    private var locationSubscription = Disposables.empty()
    private var lvl: Float = 2.0f
    private lateinit var lastLatLng: Location

    fun startTraking(){
        startLocationSensor()
        startHoleSensor()
    }

    fun startHoleSensor(){
        var time = System.currentTimeMillis()
        sensorSubsciption = sensor.subscribe(object : Subscriber<ReactiveSensorEvent>() {
            override fun onCompleted() {}

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
            }

            override fun onNext(event: ReactiveSensorEvent) {
                val gX = event.sensorEvent.values[0] / SensorManager.GRAVITY_EARTH
                val gY = event.sensorEvent.values[1] / SensorManager.GRAVITY_EARTH
                val gZ = event.sensorEvent.values[2] / SensorManager.GRAVITY_EARTH
                val gForce = Math.sqrt((gX * gX).toDouble() + (gY * gY).toDouble() + (gZ * gZ).toDouble())
                view?.updateGraph(gForce)

                if (gX > lvl  && System.currentTimeMillis() - time > 1000) {  onHole("x", gX.toDouble());  time = System.currentTimeMillis() }
                if (gY > lvl  && System.currentTimeMillis() - time > 1000) {  onHole("y", gY.toDouble());  time = System.currentTimeMillis() }
                if (gZ > lvl  && System.currentTimeMillis() - time > 1000) {  onHole("z", gZ.toDouble());  time = System.currentTimeMillis() }


            }
        })
    }

    fun startLocationSensor(){
        locationSubscription = locSensor
                .updates(locationRequest)
                .subscribe{
                    lastLatLng = it
                }
    }


    fun onHole(axis: String, lvl: Double){

        if (lastLatLng.hasSpeed()){
            Log.d("speed", " "  + lastLatLng.speed)
        }

        view?.updateBackgroundColor()
        if (lastLatLng.latitude != null){
            var lat = lastLatLng.latitude
            var lng = lastLatLng.longitude
            createHoleUseCase.execute(TrackingObserver(), CreateHoleUseCase.Params.withCoords(lat, lng, lvl, axis))
        }

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

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(res: AbsResponseEntity) {
            Log.d("result ->", " " + res.status)
        }
    }
}