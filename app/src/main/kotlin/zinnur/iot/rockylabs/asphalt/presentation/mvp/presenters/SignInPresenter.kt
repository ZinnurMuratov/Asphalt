package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import zinnur.iot.rockylabs.asphalt.domain.iteractor.LoginUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SignInView
import javax.inject.Inject
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.AuthResponseEntity




/**
 * Created by Zinnur on 13.02.17.
 */

class SignInPresenter @Inject constructor(var loginUseCase: LoginUseCase) : MvpBasePresenter<SignInView>(){

    fun login(email: String, password: String) {
        view!!.showError(false)
        view!!.showLoading()
        loginUseCase.execute(LoginObserver(), LoginUseCase.Params.forUser(email, password))
    }

    fun onDestroyView(){
        loginUseCase.dispose()
    }

    private inner class LoginObserver : DisposableObserver<AuthResponseEntity>() {

        override fun onComplete() {
            view!!.hideLoading()
            view!!.navigateToMain()
        }

        override fun onError(e: Throwable) {
            view!!.hideLoading()
            view!!.showError(true)
            e.printStackTrace()
        }

        override fun onNext(auth: AuthResponseEntity) {
            Log.d("result ->", " " + auth.tokenEntity.accessToken)
        }
    }
}