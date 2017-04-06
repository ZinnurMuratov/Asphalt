package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 04.04.17.
 */

interface SignUpView : MvpView {

    fun showError(show: Boolean)

    fun showLoading()

    fun hideLoading()

    fun showForm()

    fun navigateToMain()

}