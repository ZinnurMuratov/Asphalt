package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.PermissionAskLayout
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView

/**
 * Created by Zinnur on 16.02.17.
 */
class NoticePermissonsController : Controller(){

    private val viewBinder = PermissionAskLayout()
    private lateinit var activityCallback: MainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        activityCallback =  activity as MainView
        activityCallback.changeTitle("")
        activityCallback.lockDrawer(true)
        activityCallback.setHomeEnabled(true)
        return viewBinder.bind(this)
    }



}