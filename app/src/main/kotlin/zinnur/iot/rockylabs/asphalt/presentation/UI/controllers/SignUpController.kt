package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.animation.LayoutTransition
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import com.bluelinelabs.conductor.Controller
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.SignInControllerLayout
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.SignUpControllerLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.SignInPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.SignUpPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SignInView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SignUpView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.SignInViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.SignUpViewState
import zinnur.iot.rockylabs.asphalt.presentation.navigator
import zinnur.iot.rockylabs.asphalt.presentation.utils.isEmailValid
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 13.02.17.
 */

class SignUpController : MvpViewStateController<SignUpView, SignUpPresenter, SignUpViewState>(), SignUpView {

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): SignUpPresenter = daggerComponent.signUpPresenter()

    override fun createViewState()  = SignUpViewState()

    override fun onNewViewStateInstance() { showForm() }

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
    var activityCallback: MainView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        activityCallback = activity as MainView
        initProgress()
        return viewBinder.bind(this)
    }

    fun initProgress(){
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }


    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        presenter.onDestroyView()
    }

    override fun showError(show: Boolean) {
        if (show) {
            viewState.setShowError()
            container!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            container!!.startAnimation(shake)
            errorView!!.visibility = View.VISIBLE
        } else {
            errorView!!.visibility = View.GONE
        }
    }

    override fun showForm() {
        viewState.setShowForm()
        showError(false)
        hideLoading()
        setFormEnabled(true)
    }


    override fun showLoading() {
        viewState.setShowLoading()
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    override fun navigateToMain() {
        activityCallback?.getUser()
        navigator.showTracking(true)
    }

    fun onSignUpClicked(){

        val mail = email!!.text.toString()
        val username = name!!.text.toString()
        val pass = password!!.text.toString()
        val confirmPass = repeatPass!!.text.toString()

        container!!.clearAnimation()
        if (!isEmailValid(mail)) {
            email!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            email!!.startAnimation(shake)
            return
        }

        if (TextUtils.isEmpty(username)) {
            name!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            name!!.startAnimation(shake)
            return
        }

        if (TextUtils.isEmpty(pass)) {
            password!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            password!!.startAnimation(shake)
            return
        }

        if (TextUtils.isEmpty(pass)) {
            repeatPass!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            repeatPass!!.startAnimation(shake)
            return
        }

        if (!pass.equals(confirmPass)) {
            password!!.clearAnimation()
            repeatPass!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            repeatPass!!.startAnimation(shake)
            password!!.startAnimation(shake)
            return
        }



        presenter.signUp(mail,username,pass)
    }

    fun setFormEnabled(enabled : Boolean){
        email!!.isEnabled = enabled
        password!!.isEnabled = enabled
        signUp!!.isEnabled = enabled
    }

}