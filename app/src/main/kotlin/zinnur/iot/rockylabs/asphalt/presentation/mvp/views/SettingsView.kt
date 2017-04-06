package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 06.04.17.
 */
interface SettingsView : MvpView{

    fun onLogout()

    fun showError()

    fun showInit()
}