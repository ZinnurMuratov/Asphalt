package zinnur.iot.rockylabs.asphalt.domain.iteractor

import io.reactivex.Observable
import io.reactivex.Scheduler
import zinnur.iot.rockylabs.asphalt.data.entity.response.AuthResponseEntity
import zinnur.iot.rockylabs.asphalt.data.entity.request.SignUpRequstBody
import zinnur.iot.rockylabs.asphalt.data.service.AuthService
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.domain.model.UserAuthCredentialsModel
import javax.inject.Inject

/**
 * Created by Zinnur on 04.04.17.
 */

class SignUpUseCase @Inject
internal constructor(private val authService: AuthService, scheduler: Scheduler, val authPreferences: AuthPreferences)
    : UseCase<AuthResponseEntity, SignUpUseCase.Params>(scheduler) {

    override fun buildUseCaseObservable(params: Params): Observable<AuthResponseEntity> {
        return authService
                .signUp(SignUpRequstBody(params.email, params.name, params.password))
                .doOnNext{ authPreferences.clear() }
                .doOnNext{ authPreferences
                        .saveAuthCredentialsModel(UserAuthCredentialsModel(it.tokenEntity.accessToken,
                                it.tokenEntity.refreshToken))}

    }


    class Params private constructor(val email: String, val name: String, val password: String) {
        companion object {

            fun withCredentials(email: String, name: String, password: String): Params {
                return Params(email, name, password)
            }
        }
    }
}
