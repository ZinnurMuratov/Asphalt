package zinnur.iot.rockylabs.asphalt.presentation.di.modules

import android.content.Context
import dagger.Module
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.patloew.rxlocation.RxLocation
import com.patloew.rxlocation.FusedLocation
import dagger.Provides
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter
import android.hardware.SensorManager
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import com.google.android.gms.location.LocationRequest
import org.jetbrains.annotations.Nullable
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zinnur.iot.rockylabs.asphalt.presentation.di.scopes.ControllerScope
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by Zinnur on 28.02.17.
 */
@Module
class SensorModule{



    @ControllerScope
    @Nullable
    @Provides
    fun provideAccelerometerSensor(context: Context, reactiveSensors: ReactiveSensors): Observable<ReactiveSensorEvent>? {
        if (reactiveSensors.hasSensor(Sensor.TYPE_ACCELEROMETER)) {
            return ReactiveSensors(context).observeSensor(Sensor.TYPE_ACCELEROMETER, SensorManager.SENSOR_DELAY_NORMAL)
                    .subscribeOn(Schedulers.computation())
                    .filter(ReactiveSensorFilter.filterSensorChanged())
                    .observeOn(AndroidSchedulers.mainThread())
        }
        return null
    }

    @ControllerScope
    @Nullable
    @Provides
    fun providesLocationSensor(context: Context, rxLocation: RxLocation): FusedLocation? {
        val pm = context.packageManager
        if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION)) {
            return rxLocation.location()
        }
        return null
    }

    @ControllerScope
    @Provides
    fun provideLocationRequset(): LocationRequest {
        return LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(2000)
    }






}