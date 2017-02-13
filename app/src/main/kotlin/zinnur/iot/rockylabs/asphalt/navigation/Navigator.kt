package zinnur.iot.rockylabs.asphalt.navigation

import android.view.View
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import zinnur.iot.rockylabs.asphalt.UI.controllers.SignInController
import zinnur.iot.rockylabs.asphalt.UI.controllers.SignUpController

/**
 * Created by Zinnur on 10.02.17.
 */

interface Navigator {

    val router: Router

    fun showSignIn(){
        router.pushController(RouterTransaction.with(SignInController()))
    }

    fun showSignUp(){
        router.pushController(RouterTransaction.with(SignUpController()))
    }
}