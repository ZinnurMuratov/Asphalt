package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.UserResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.UserUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import javax.inject.Inject

/**
 * Created by Zinnur on 06.04.17.
 */

class MainActivityPresenter @Inject constructor(var userUseCase: UserUseCase ) : MvpBasePresenter<MainView>(){

    fun getUser(){


        userUseCase.execute(UserObserver(), UserUseCase.Params.withId("own"))

    }

    private inner class UserObserver : DisposableObserver<UserResponseEntity>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(user: UserResponseEntity) {
            Log.d("result ->", " " + user.status)
        }
    }
}
