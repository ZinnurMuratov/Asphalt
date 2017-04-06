package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.hardware.SensorManager
import android.location.Location
import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingView
import javax.inject.Inject
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import com.patloew.rxlocation.FusedLocation
import io.reactivex.disposables.Disposables
import org.jetbrains.annotations.Nullable
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.subscriptions.Subscriptions






/**
 * Created by Zinnur on 28.02.17.
 */
class TrackingPresenter @Inject constructor(@Nullable var sensor: Observable<ReactiveSensorEvent>,
                                            @Nullable var locSensor: FusedLocation) : MvpBasePresenter<TrackingView>(){

    private var sensorSubsciption = Subscriptions.empty()
    private var locationSubscription = Disposables.empty()
    private var lvl: Int = 3
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

                if (gForce >= lvl && System.currentTimeMillis() - time > 1000) {
                    time = System.currentTimeMillis()
                    onHole()
                }

            }
        })
    }

    fun startLocationSensor(){
        locationSubscription = locSensor
                .lastLocation()
                .subscribe{
                    lastLatLng = it
                }
    }


    fun onHole(){
        view?.updateBackgroundColor()
        Log.d("loc ->", lastLatLng.toString())
    }



    fun stopTracking(){
        if (!sensorSubsciption?.isUnsubscribed!!){
            sensorSubsciption?.unsubscribe()
        }

        if (!locationSubscription?.isDisposed!!){
            locationSubscription?.dispose()
        }
    }

    fun setSensetiveLvl(lvl: Int){
        this.lvl = lvl
    }
}