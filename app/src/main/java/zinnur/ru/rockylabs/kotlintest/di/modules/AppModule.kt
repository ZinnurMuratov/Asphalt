package zinnur.ru.rockylabs.kotlintest.di.modules

import android.app.Application

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by Zinnur on 12.01.17.
 */

@Module
class AppModule(internal var mApplication: Application) {

    @Provides
    @Singleton
    internal fun providesApplication(): Application {
        return mApplication
    }
}