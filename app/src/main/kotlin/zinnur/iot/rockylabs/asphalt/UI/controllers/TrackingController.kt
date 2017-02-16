package zinnur.iot.rockylabs.asphalt.UI.controllers

import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import zinnur.iot.rockylabs.asphalt.UI.anko.TrackingControllerLayout
import zinnur.iot.rockylabs.asphalt.mvp.views.viewStates.MainView

/**
 * Created by Zinnur on 15.02.17.
 */
class TrackingController : Controller(){

    private val viewBinder = TrackingControllerLayout()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as MainView).lockDrawer(false)
        return viewBinder.bind(this)
    }

}