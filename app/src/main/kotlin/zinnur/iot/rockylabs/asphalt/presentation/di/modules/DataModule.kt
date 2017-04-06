package zinnur.iot.rockylabs.asphalt.presentation.di.modules

import android.content.Context
import dagger.Module
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Provides
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import javax.inject.Singleton



/**
 * Created by Zinnur on 04.04.17.
 */
@Module
class DataModule{

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("ASPHALT", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthPreferences(preferences: SharedPreferences, gson: Gson): AuthPreferences {
        return AuthPreferences(preferences, gson)
    }
}