package zinnur.iot.rockylabs.asphalt.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import android.content.pm.PackageManager
import android.hardware.Sensor
import com.patloew.rxlocation.RxLocation
import com.patloew.rxlocation.FusedLocation
import dagger.Provides
import zinnur.iot.rockylabs.asphalt.di.scopes.SensorScope
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter
import android.hardware.SensorManager
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import org.jetbrains.annotations.Nullable
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import zinnur.iot.rockylabs.asphalt.di.scopes.ControllerScope
import javax.inject.Named


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




}