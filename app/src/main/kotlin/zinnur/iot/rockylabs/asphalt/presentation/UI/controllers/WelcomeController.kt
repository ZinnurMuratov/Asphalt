package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.app.Activity
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.WelcomeControllerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.WelcomePresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.WelcomeView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.WelcomeViewState
import android.widget.*
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView


/**
 * Created by Zinnur on 11.01.17.
 */

class WelcomeController : WelcomeView, MvpViewStateController<WelcomeView, WelcomePresenter, WelcomeViewState>()
{
    var background: FrameLayout? = null
    var logo:       TextView? = null
    var slogan:     TextView? = null
    var signIn:     Button? = null
    var signUp:     Button? = null
    var container:  LinearLayout? = null
    var activityCallback: MainView? = null

    private val viewBinder = WelcomeControllerLayout()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (activity as MainView).showToolbar(false)
        return viewBinder.bind(this)
    }

    override fun onActivityStarted(activity: Activity) {
        super.onActivityStarted(activity)
        activityCallback =  activity as MainView
        activityCallback!!.lockDrawer(true)
    }



    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        viewBinder.unbind(this)
        activityCallback = null
    }

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): WelcomePresenter = daggerComponent.welcomePresenter()

    override fun onNewViewStateInstance() {}

    override fun createViewState() = WelcomeViewState()
}