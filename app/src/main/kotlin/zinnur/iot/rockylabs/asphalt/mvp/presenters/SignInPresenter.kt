package zinnur.iot.rockylabs.asphalt.mvp.presenters

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import rx.Subscription
import zinnur.iot.rockylabs.asphalt.mvp.views.SignInView
import javax.inject.Inject

/**
 * Created by Zinnur on 13.02.17.
 */

class SignInPresenter @Inject constructor() : MvpBasePresenter<SignInView>(){

    private lateinit var subscription: Subscription


    fun login(){
    }
}