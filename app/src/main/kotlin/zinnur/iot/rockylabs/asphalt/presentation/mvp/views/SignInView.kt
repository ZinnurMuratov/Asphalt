package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 13.02.17.
 */
interface SignInView : MvpView {

    fun showError(show: Boolean)

    fun showLoading()

    fun hideLoading()

    fun showForm()

    fun navigateToMain()

}