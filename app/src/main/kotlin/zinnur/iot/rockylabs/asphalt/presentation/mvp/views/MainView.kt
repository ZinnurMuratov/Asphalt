package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 15.02.17.
 */

interface MainView : MvpView {
    fun lockDrawer(lock: Boolean)

    fun showToolbar(show: Boolean)

    fun changeTitle(title: String)

    fun setHomeEnabled(enable: Boolean)

    fun syncToggle()
}