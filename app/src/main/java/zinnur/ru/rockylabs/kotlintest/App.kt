package zinnur.ru.rockylabs.kotlintest

import android.app.Application

import zinnur.ru.rockylabs.kotlintest.di.components.AppComponent
import zinnur.ru.rockylabs.kotlintest.di.components.DaggerAppComponent
import zinnur.ru.rockylabs.kotlintest.di.components.DaggerNetComponent
import zinnur.ru.rockylabs.kotlintest.di.components.NetComponent
import zinnur.ru.rockylabs.kotlintest.di.modules.ApiModule
import zinnur.ru.rockylabs.kotlintest.di.modules.AppModule
import zinnur.ru.rockylabs.kotlintest.di.modules.NetModule

/**
 * Created by Zinnur on 12.01.17.
 */

class App : Application() {


    companion object {
        @JvmStatic lateinit var graphApp: AppComponent
        @JvmStatic lateinit var graphNet: NetComponent
    }

    override fun onCreate() {
        super.onCreate()

        graphNet = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://api.github.com/"))
                .build()

        graphApp = DaggerAppComponent.builder()
                .netComponent(graphNet)
                .apiModule(ApiModule())
                .build()

    }
}