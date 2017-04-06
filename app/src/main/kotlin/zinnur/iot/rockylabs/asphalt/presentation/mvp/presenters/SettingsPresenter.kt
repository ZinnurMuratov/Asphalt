package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SettingsView
import javax.inject.Inject

/**
 * Created by Zinnur on 06.04.17.
 */
class SettingsPresenter
@Inject constructor(var authPreferences: AuthPreferences) : MvpBasePresenter<SettingsView>(){

    fun logout(){
        authPreferences.clear()
        view?.onLogout()
    }
}