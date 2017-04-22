package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import android.app.Activity
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.miguelbcr.ui.rx_paparazzo2.entities.size.Size
import com.yalantis.ucrop.UCrop
import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.MainActivity
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.CameraController

/**
 * Created by Zinnur on 15.02.17.
 */

interface MainView : MvpView {
    fun lockDrawer(lock: Boolean)

    fun showToolbar(show: Boolean)

    fun changeTitle(title: String)

    fun setHomeEnabled(enable: Boolean)

    fun syncToggle()

    fun setUserData(name: String, email: String)

    fun getUser()

    fun pickSingle(options: UCrop.Options?, size: Size): RxPaparazzo.SingleSelectionBuilder<MainActivity>
}