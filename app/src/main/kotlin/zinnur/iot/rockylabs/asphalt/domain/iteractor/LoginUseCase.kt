package zinnur.iot.rockylabs.asphalt.domain.iteractor

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.AuthResponseEntity
import zinnur.iot.rockylabs.asphalt.data.entity.LoginRequestBody
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.domain.model.UserAuthCredentialsModel

/**
 * Created by Zinnur on 04.04.17.
 */

class LoginUseCase @Inject
internal constructor(private val authService: AuthService, scheduler: Scheduler, val authPreferences: AuthPreferences)
                                    : UseCase<AuthResponseEntity, LoginUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<AuthResponseEntity> {
        return authService
                .login(LoginRequestBody(params.email, params.password))
                .doOnNext{ authPreferences.clear() }
                .doOnNext{ authPreferences
                        .saveAuthCredentialsModel(UserAuthCredentialsModel(it.tokenEntity.accessToken,
                                                                            it.tokenEntity.refreshToken))}

    }


    class Params private constructor(val email: String, val password: String) {
        companion object {

            fun forUser(email: String, password: String): Params {
                return Params(email, password)
            }
        }
    }
}
