package zinnur.iot.rockylabs.asphalt.presentation.di.modules

import android.content.Context
import com.patloew.rxlocation.RxLocation
import com.squareup.picasso.Picasso

import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import zinnur.iot.rockylabs.asphalt.presentation.di.scopes.ApplicationContext
import com.github.pwittchen.reactivesensors.library.ReactiveSensors
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


/**
 * Created by Zinnur on 12.01.17.
 */

@Module
class ApplicationModule(internal var applicationContext: Context) {

    @Singleton
    @ApplicationContext
    @Provides
    fun provideApplicationContext() = applicationContext

    @Singleton
    @Provides
    fun providePicasso() = Picasso.with(applicationContext)


    @Provides
    @Singleton
    fun providesRxLocation(): RxLocation {
        return RxLocation(applicationContext)
    }


    @Singleton
    @Provides
    fun providesReactiveSensors(): ReactiveSensors {
        return ReactiveSensors(applicationContext)
    }

    @Provides
    @Singleton
    fun provideTasksScheduler(): Scheduler {
        return Schedulers.io()
    }
}
