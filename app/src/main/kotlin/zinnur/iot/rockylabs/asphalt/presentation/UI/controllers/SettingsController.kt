package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.SettingsLayout
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import android.support.v7.app.AlertDialog
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.SettingsPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SettingsView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.SettingsViewState
import zinnur.iot.rockylabs.asphalt.presentation.navigator


/**
 * Created by Zinnur on 16.02.17.
 */

class SettingsController : MvpViewStateController<SettingsView, SettingsPresenter, SettingsViewState>(), SettingsView{


    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): SettingsPresenter = daggerComponent.settingsPresenter()

    override fun createViewState() = SettingsViewState()

    override fun onNewViewStateInstance() { showInit() }



    var logout: TextView? = null
    private lateinit var activityCallback: MainView

    private val viewBinder = SettingsLayout()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        activityCallback =  activity as MainView
        activityCallback.changeTitle("Settings")
        return viewBinder.bind(this)
    }

    fun openLogoutDialog(){
        val builder = AlertDialog.Builder(activity!!)
        builder.setCancelable(true)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton("YES", { dialog, which -> presenter.logout(); dialog.dismiss()})
        builder.setNegativeButton("NO", { dialog, which -> dialog.dismiss()})
        builder.show()
    }

    override fun onLogout() {
        navigator.showWelcome(true)
    }

    override fun showInit() {
        //TODO
    }

    override fun showError() {
        //TODO
    }

}