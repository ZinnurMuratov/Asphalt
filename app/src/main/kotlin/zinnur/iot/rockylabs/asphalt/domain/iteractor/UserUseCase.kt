package zinnur.iot.rockylabs.asphalt.domain.iteractor

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.UserResponseEntity
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import javax.inject.Inject


/**
 * Created by Zinnur on 06.04.17.
 */
class UserUseCase @Inject internal constructor(private val authService: AuthService, scheduler: Scheduler)
    : UseCase<UserResponseEntity, UserUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<UserResponseEntity> {
        return authService
                .user()
    }

    fun test(){
        Log.d("hee", "hui")
    }

    class Params private constructor(val id: String) {
        companion object {

            fun withId(id: String): Params {
                return Params(id)
            }
        }
    }


}