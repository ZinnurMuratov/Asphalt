package zinnur.iot.rockylabs.asphalt.data.service

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import zinnur.iot.rockylabs.asphalt.data.entity.*

/**
 * Created by Zinnur on 09.04.17.
 */
interface TrackingService{

    @POST("api/v1/hole")
    fun create(@Body body: CreateHoleRequest): Observable<AbsResponseEntity>

    @POST("api/v1/holeWithPhoto")
    fun create(@Body body: CreatePhotoHoleRequest): Observable<AbsResponseEntity>

    @GET("api/v1/holes")
    fun get(@Query("city") city: String) : Observable<GetHoleResponseEntity>

    @GET("/api/v1/feed")
    fun getFeed(@Query("city") city: String,
                @Query("perpage") limit: Long,
                @Query("page") page: Int) : Observable<GetFeedResponseEntity>


}