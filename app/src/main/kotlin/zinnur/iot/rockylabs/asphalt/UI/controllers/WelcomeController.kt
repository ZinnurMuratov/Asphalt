package zinnur.iot.rockylabs.asphalt.UI.controllers

import zinnur.iot.rockylabs.asphalt.UI.anko.WelcomeControllerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import kotlin.ru.rockylabs.kotlintest.R
import android.widget.TextView
import android.widget.LinearLayout
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.lce.data.RetainingLceViewState
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zinnur.iot.rockylabs.asphalt.daggerComponent
import zinnur.iot.rockylabs.asphalt.mvp.presenters.WelcomePresenter
import zinnur.iot.rockylabs.asphalt.mvp.views.WelcomeView
import zinnur.iot.rockylabs.asphalt.mvp.views.WelcomeViewState


/**
 * Created by Zinnur on 11.01.17.
 */

class WelcomeController : WelcomeView, MvpViewStateController<WelcomeView, WelcomePresenter, WelcomeViewState>()
{
    var background: LinearLayout? = null
    var actionText: TextView? = null
    private val viewBinder = WelcomeControllerLayout()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = viewBinder.bind(this)


    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        viewBinder.unbind(this)
    }

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): WelcomePresenter = daggerComponent.welcomePresenter()

    override fun onNewViewStateInstance() {}

    override fun createViewState() = WelcomeViewState()





}