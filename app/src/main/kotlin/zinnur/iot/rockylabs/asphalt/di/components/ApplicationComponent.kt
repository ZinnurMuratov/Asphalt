package zinnur.iot.rockylabs.asphalt.di.components

import android.content.Context
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import com.patloew.rxlocation.RxLocation
import com.squareup.picasso.Picasso
import dagger.Component
import zinnur.iot.rockylabs.asphalt.AsphaltApp
import zinnur.iot.rockylabs.asphalt.di.modules.ApplicationModule
import javax.inject.Singleton

/**
 * Created by Zinnur on 12.01.17.
 */

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {

    fun context(): Context

    fun picasso(): Picasso

    fun reactiveSensors(): ReactiveSensors

    fun rxLocation(): RxLocation

    fun inject(app: AsphaltApp)
}