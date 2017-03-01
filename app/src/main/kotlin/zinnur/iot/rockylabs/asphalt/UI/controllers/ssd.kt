package zinnur.iot.rockylabs.asphalt.UI.controllers

import android.hardware.Sensor
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

import com.github.pwittchen.reactivesensors.library.ReactiveSensorEvent
import com.github.pwittchen.reactivesensors.library.ReactiveSensorFilter
import com.github.pwittchen.reactivesensors.library.ReactiveSensors

import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Zinnur on 25.02.17.
 */

class ssd : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        ReactiveSensors(this).observeSensor(Sensor.TYPE_GYROSCOPE)
                .subscribeOn(Schedulers.computation())
                .filter(ReactiveSensorFilter.filterSensorChanged())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ReactiveSensorEvent>() {
                    override fun onCompleted() {
                        // subscription completed
                    }

                    override fun onError(throwable: Throwable) {

                    }

                    override fun onNext(event: ReactiveSensorEvent) {
                        // handle event
                    }
                })
    }
}
