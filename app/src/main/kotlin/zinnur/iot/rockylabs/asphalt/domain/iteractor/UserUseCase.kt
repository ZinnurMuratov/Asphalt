package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.UserResponseEntity
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import javax.inject.Inject


/**
 * Created by Zinnur on 06.04.17.
 */
class UserUseCase @Inject internal constructor(private val authService: AuthService,
                                               private var authPreferences: AuthPreferences,
                                               scheduler: Scheduler)
    : UseCase<UserResponseEntity, UserUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<UserResponseEntity> {
        return authService
                .user()
                .doOnNext{
                    it.user?.let {
                        val res = it
                        val oldUser = authPreferences.userAuthCredentials
                        oldUser?.let {
                            authPreferences.clear()
                            authPreferences.saveAuthCredentialsModel(oldUser.updateWithUserData(res.name, res.email))
                        }
                    }
                }
    }


    class Params private constructor(val id: String) {
        companion object {

            fun withId(id: String): Params {
                return Params(id)
            }
        }
    }


}