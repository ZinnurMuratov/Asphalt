package zinnur.iot.rockylabs.asphalt.data.service

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import zinnur.iot.rockylabs.asphalt.data.entity.AuthResponseEntity
import zinnur.iot.rockylabs.asphalt.data.entity.LoginRequestBody
import zinnur.iot.rockylabs.asphalt.data.entity.SignUpRequstBody
import zinnur.iot.rockylabs.asphalt.data.entity.UserResponseEntity
import android.support.annotation.NonNull
import com.annimon.stream.function.Consumer
import okhttp3.*


/**
 * Created by Zinnur on 30.03.17.
 */
interface AuthService{

    @POST("api/auth/sign_in")
    fun login(@Body body: LoginRequestBody): Observable<AuthResponseEntity>

    @POST("api/auth/sign_up")
    fun signUp(@Body body: SignUpRequstBody): Observable<AuthResponseEntity>


    @GET("api/v1/user")
    fun user(): Observable<UserResponseEntity>


    object RefreshHelper {

        fun refresh(okHttpClient: OkHttpClient, baseUrl: String, refreshToken: String,
                    onError: Consumer<Exception>): Headers {

            val formBuilder = FormBody.Builder()
                    .addEncoded("refreshToken", refreshToken)
                    .build()

            val request = Request.Builder()
                    .url(baseUrl + "api/auth/getNewToken")
                    .post(formBuilder)
                    .build()

            try {
                return okHttpClient.newCall(request).execute().headers()
            } catch (e: Exception) {
                e.printStackTrace()
                onError.accept(e)
                null!!
            }

        }
    }

}