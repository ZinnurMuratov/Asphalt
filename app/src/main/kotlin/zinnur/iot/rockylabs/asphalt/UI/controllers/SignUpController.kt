package zinnur.iot.rockylabs.asphalt.UI.controllers

import android.animation.LayoutTransition
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bluelinelabs.conductor.Controller
import zinnur.iot.rockylabs.asphalt.UI.anko.SignInControllerLayout
import zinnur.iot.rockylabs.asphalt.UI.anko.SignUpControllerLayout
import zinnur.iot.rockylabs.asphalt.navigator

/**
 * Created by Zinnur on 13.02.17.
 */

class SignUpController : Controller(){

    var background: FrameLayout? = null
    var container:  LinearLayout? = null
    var email:      EditText? = null
    var name:       EditText? = null
    var password:   EditText? = null
    var repeatPass: EditText? = null
    var signUp:     Button? = null
    var headText:   TextView? = null
    var transition: LayoutTransition? = null
    var errorView:  TextView? = null
    private lateinit var progressDialog: ProgressDialog
    private val viewBinder = SignUpControllerLayout()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        return viewBinder.bind(this)    }

    fun onSignUpClicked(){
        navigator.showTracking(true)
    }

}