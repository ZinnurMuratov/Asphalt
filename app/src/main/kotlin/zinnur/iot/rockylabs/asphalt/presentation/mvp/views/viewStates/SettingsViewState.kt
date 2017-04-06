package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SettingsView

/**
 * Created by Zinnur on 06.04.17.
 */
class SettingsViewState : ViewState<SettingsView> {

    enum class State {
        SHOW_ERROR,
        SHOW_INIT
    }

    private var state = State.SHOW_ERROR

    override fun apply(view: SettingsView, retained: Boolean) = when (state) {
        State.SHOW_ERROR -> view.showError()
        State.SHOW_INIT -> view.showInit()
    }


    fun setShowError() {
        state = State.SHOW_ERROR
    }

    fun setShowInit(){
        state = State.SHOW_INIT
    }

}