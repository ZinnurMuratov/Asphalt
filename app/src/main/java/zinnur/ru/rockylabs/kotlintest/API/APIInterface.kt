package zinnur.ru.rockylabs.kotlintest.API

import java.util.ArrayList

import zinnur.ru.rockylabs.kotlintest.models.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Zinnur on 12.01.17.
 */

interface APIInterface {

    @GET("users/{user}/repos")
    fun getRepository(@Path("user") userName: String): Observable<List<Repository>>
}
