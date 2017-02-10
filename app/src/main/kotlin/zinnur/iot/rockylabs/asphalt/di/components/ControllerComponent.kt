package zinnur.iot.rockylabs.asphalt.di.components

import dagger.Component
import zinnur.iot.rockylabs.asphalt.di.modules.ControllerModule
import zinnur.iot.rockylabs.asphalt.di.scopes.ControllerScope
import zinnur.iot.rockylabs.asphalt.mvp.presenters.WelcomePresenter
import zinnur.iot.rockylabs.asphalt.navigation.Navigator

/**
 * Created by Zinnur on 10.02.17.
 */

@Component(modules = arrayOf(ControllerModule::class), dependencies = arrayOf(ApplicationComponent::class))
@ControllerScope
interface ControllerComponent {

    fun navigator(): Navigator

    fun welcomePresenter(): WelcomePresenter
}