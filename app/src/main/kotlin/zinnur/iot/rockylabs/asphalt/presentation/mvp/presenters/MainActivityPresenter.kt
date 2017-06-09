package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.response.UserResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UserUseCase
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.ifParamsNotNull
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.safeLet
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import javax.inject.Inject

/**
 * Created by Zinnur on 06.04.17.
 */

class MainActivityPresenter @Inject constructor(var userUseCase: UserUseCase,
                                                val authPreferences: AuthPreferences) : MvpBasePresenter<MainView>(){

    fun getUser(){
        getFromPrefs()
        userUseCase.execute(UserObserver(), UserUseCase.Params.withId("own"))

    }

    private fun getFromPrefs(){
        val user = authPreferences.userAuthCredentials
        ifParamsNotNull(user?.name, user?.email)?.let { view.setUserData(it[0], it[1])}
    }

    private inner class UserObserver : DisposableObserver<UserResponseEntity>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(user: UserResponseEntity) {
            Log.d("result ->", " " + user.user.name)
            ifParamsNotNull(user.user.name, user.user.email)?.let {
                view?.setUserData(it[0], it[1])
            }
        }
    }
}
