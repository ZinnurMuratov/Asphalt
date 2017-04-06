package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SignUpView

/**
 * Created by Zinnur on 04.04.17.
 */

class SignUpViewState : ViewState<SignUpView> {
    enum class State {
        SHOW_FORM, SHOW_LOADING, SHOW_ERROR
    }

    private var state = State.SHOW_FORM

    override fun apply(view: SignUpView, retained: Boolean) = when (state) {
        State.SHOW_FORM -> view.showForm()
        State.SHOW_LOADING -> view.showLoading()
        State.SHOW_ERROR -> view.showError(true)
    }

    fun setShowForm() {
        state = State.SHOW_FORM
    }

    fun setShowLoading() {
        state = State.SHOW_LOADING
    }

    fun setShowError() {
        state = State.SHOW_ERROR
    }
}