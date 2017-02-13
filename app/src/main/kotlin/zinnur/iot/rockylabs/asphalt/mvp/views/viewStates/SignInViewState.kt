package zinnur.iot.rockylabs.asphalt.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.mvp.views.SignInView

/**
 * Created by Zinnur on 13.02.17.
 */
class SignInViewState : ViewState<SignInView> {
    enum class State {
        SHOW_FORM, SHOW_LOADING, SHOW_ERROR
    }

    private var state = State.SHOW_FORM

    override fun apply(view: SignInView, retained: Boolean) = when (state) {
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