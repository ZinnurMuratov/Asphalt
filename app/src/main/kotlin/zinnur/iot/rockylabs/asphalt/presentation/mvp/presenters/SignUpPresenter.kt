package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.AuthResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.SignUpUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SignUpView
import javax.inject.Inject

/**
 * Created by Zinnur on 04.04.17.
 */

class SignUpPresenter @Inject constructor(var signUpUseCase: SignUpUseCase) : MvpBasePresenter<SignUpView>(){



    fun signUp(email: String, name: String, password: String) {
        view?.showLoading()
        signUpUseCase.execute(SignUpObserver(), SignUpUseCase.Params.withCredentials(email,name, password))
    }

    fun onDestroyView(){
        signUpUseCase.dispose()
    }

    private inner class SignUpObserver : DisposableObserver<AuthResponseEntity>() {

        override fun onComplete() {
            view!!.hideLoading()
            view!!.navigateToMain()
        }

        override fun onError(e: Throwable) {
            view!!.hideLoading()
            e.printStackTrace()
            view!!.showError(true)
        }

        override fun onNext(auth: AuthResponseEntity) {
            Log.d("result ->", " " + auth.tokenEntity.accessToken)
        }
    }
}