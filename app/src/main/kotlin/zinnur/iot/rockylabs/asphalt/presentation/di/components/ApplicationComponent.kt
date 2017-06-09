package zinnur.iot.rockylabs.asphalt.presentation.di.components

import android.content.Context
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import com.patloew.rxlocation.RxLocation
import com.squareup.picasso.Picasso
import dagger.Component
import io.reactivex.Scheduler
import retrofit2.Retrofit
import zinnur.iot.rockylabs.asphalt.presentation.AsphaltApp
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.ApplicationModule
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.NetworkModule
import javax.inject.Singleton
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import com.google.gson.Gson
import zinnur.iot.rockylabs.asphalt.data.service.TrackingService
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.domain.repository.RealmController
import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.MainActivity
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.CachingModule
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.DataModule
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.MainActivityPresenter


/**
 * Created by Zinnur on 12.01.17.
 */

@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, DataModule::class, CachingModule::class))
@Singleton
interface ApplicationComponent {

    fun context(): Context

    fun picasso(): Picasso

    fun reactiveSensors(): ReactiveSensors

    fun rxLocation(): RxLocation

    fun tasksScheduler(): Scheduler

    fun retrofit(): Retrofit

    fun authService(): AuthService

    fun trackingService(): TrackingService

    fun gson(): Gson

    fun inject(app: AsphaltApp)

    fun authPreferences(): AuthPreferences

    fun inject(a: MainActivity)

    fun mainPresenter(): MainActivityPresenter

    fun realmController(): RealmController

}