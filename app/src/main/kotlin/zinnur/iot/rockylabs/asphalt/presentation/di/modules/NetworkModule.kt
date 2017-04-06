package zinnur.iot.rockylabs.asphalt.presentation.di.modules

import android.content.Context
import com.annimon.stream.function.Consumer
import dagger.Module
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import dagger.Provides
import okhttp3.*
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.presentation.utils.Consts
import retrofit2.adapter.rxjava2.Result.response
import zinnur.iot.rockylabs.asphalt.presentation.AsphaltApp
import android.widget.Toast
import zinnur.iot.rockylabs.asphalt.presentation.di.scopes.ApplicationContext
import android.icu.util.ULocale.getLanguage
import java.util.*
import com.annimon.stream.ComparatorCompat.chain
import okhttp3.logging.HttpLoggingInterceptor


/**
 * Created by Zinnur on 28.03.17.
 */
@Module
class NetworkModule {

    private val HEADER_KEY_ACCESS_TOKEN = "newToken"


    @Provides @Singleton fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create()
    }

    @Provides @Singleton fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }



    @Provides @Singleton fun provideRetrofit(client: OkHttpClient, baseUrl: HttpUrl, gson: Gson,
                                             factory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(factory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    @Provides @Singleton fun provideBaseUrl(): HttpUrl {
        return HttpUrl.parse(Consts.API_ENDPOINT)
    }



    @Provides @Singleton fun provideOkHttpClient(authPreferences: AuthPreferences, context: Context): OkHttpClient {
        val okHttpClient = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.newBuilder().readTimeout(15, TimeUnit.SECONDS)
        okHttpClient.newBuilder().connectTimeout(15, TimeUnit.SECONDS)
        okHttpClient.newBuilder().addInterceptor(interceptor)
        okHttpClient.newBuilder().addInterceptor {

            chain ->
            if (!authPreferences.isUserAuthorized()){
                null!!
            }
            val token = authPreferences.userAuthCredentials?.accessToken
            val request = chain.request().newBuilder().addHeader("Authorization", token).build()
            chain.proceed(request)
        }
        okHttpClient.newBuilder().authenticator({ route, response ->

            if (!authPreferences.isUserAuthorized()) {
                // TODO when is userNotAuthorized
                val toast = Toast.makeText(context,
                        "Пора покормить кота!", Toast.LENGTH_SHORT)
                toast.show()
                null!!
            }
            var userCredentials = authPreferences.userAuthCredentials
            var headers = AuthService.RefreshHelper.refresh(okHttpClient,
                    Consts.API_ENDPOINT,
                    userCredentials!!.accessToken,
                    Consumer<Exception>{})
            var token = headers.get(HEADER_KEY_ACCESS_TOKEN)
            authPreferences.saveAuthCredentialsModel(userCredentials.copyWithRefreshedtoken(token))


            response.request().newBuilder()
                    .headers(headers)
                    .build()

        })
        return okHttpClient.newBuilder().build()

    }




    @Provides @Singleton fun provideAuthService(retrofit: Retrofit): AuthService {
        return retrofit.create<AuthService>(AuthService::class.java)
    }








}