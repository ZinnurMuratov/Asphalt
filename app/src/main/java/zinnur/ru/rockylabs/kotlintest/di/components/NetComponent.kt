package zinnur.ru.rockylabs.kotlintest.di.components

import android.content.SharedPreferences

import javax.inject.Singleton

import zinnur.ru.rockylabs.kotlintest.di.modules.AppModule
import zinnur.ru.rockylabs.kotlintest.di.modules.NetModule
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * Created by Zinnur on 12.01.17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface NetComponent {

    fun retrofit(): Retrofit

    fun okHttpClient(): OkHttpClient

    fun sharedPreferences(): SharedPreferences
}
