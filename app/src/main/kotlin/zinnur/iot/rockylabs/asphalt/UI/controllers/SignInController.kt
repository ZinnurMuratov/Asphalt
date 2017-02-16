package zinnur.iot.rockylabs.asphalt.UI.controllers

import android.animation.LayoutTransition
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import zinnur.iot.rockylabs.asphalt.UI.anko.SignInControllerLayout
import zinnur.iot.rockylabs.asphalt.daggerComponent
import zinnur.iot.rockylabs.asphalt.mvp.presenters.SignInPresenter
import zinnur.iot.rockylabs.asphalt.mvp.views.SignInView
import zinnur.iot.rockylabs.asphalt.mvp.views.viewStates.SignInViewState
import kotlin.ru.rockylabs.kotlintest.R
import android.text.TextUtils
import zinnur.iot.rockylabs.asphalt.utils.isEmailValid

/**
 * Created by Zinnur on 13.02.17.
 */

class SignInController : MvpViewStateController<SignInView, SignInPresenter, SignInViewState>(), SignInView{


    var background: FrameLayout? = null
    var container:  LinearLayout? = null
    var email:      EditText? = null
    var password:   EditText? = null
    var signIn:     Button? = null
    var headText:   TextView? = null
    var transition: LayoutTransition? = null
    var errorView:  TextView? = null
    private lateinit var progressDialog: ProgressDialog
    private val viewBinder = SignInControllerLayout()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (activity as AppCompatActivity).supportActionBar!!.hide()
        initProgress()
        return viewBinder.bind(this)
    }

    fun initProgress(){
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }

    override fun showForm() {
        viewState.setShowForm()
        showError(false)
        hideLoading()
        setFormEnabled(true)
    }


    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): SignInPresenter = daggerComponent.signInPresenter()

    override fun createViewState()  = SignInViewState()

    override fun onNewViewStateInstance() { showForm() }


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

    override fun showLoading() {
        viewState.setShowLoading()
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.dismiss()
    }

    fun onLoginClicked(){

        val mail = email!!.text.toString()
        val pass = password!!.text.toString()

        container!!.clearAnimation()
        if (!isEmailValid(mail)) {
            email!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            email!!.startAnimation(shake)
            return
        }

        if (TextUtils.isEmpty(pass)) {
            password!!.clearAnimation()
            val shake = AnimationUtils.loadAnimation(activity, R.anim.shake)
            password!!.startAnimation(shake)
            return
        }

        showLoading()
    }

    fun setFormEnabled(enabled : Boolean){
        email!!.isEnabled = enabled
        password!!.isEnabled = enabled
        signIn!!.isEnabled = enabled
    }

}