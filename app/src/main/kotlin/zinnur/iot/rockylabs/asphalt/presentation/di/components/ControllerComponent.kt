package zinnur.iot.rockylabs.asphalt.presentation.di.components

import dagger.Component
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.ControllerModule
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.SensorModule
import zinnur.iot.rockylabs.asphalt.presentation.di.scopes.ControllerScope
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.*
import zinnur.iot.rockylabs.asphalt.presentation.navigation.Navigator

/**
 * Created by Zinnur on 10.02.17.
 */

@Component(modules = arrayOf(ControllerModule::class, SensorModule::class), dependencies = arrayOf(ApplicationComponent::class))
@ControllerScope
interface ControllerComponent {

    fun navigator(): Navigator

    fun welcomePresenter(): WelcomePresenter

    fun signInPresenter(): SignInPresenter

    fun trackingPresenter(): TrackingPresenter

    fun signUpPresenter(): SignUpPresenter

    fun settingsPresenter(): SettingsPresenter

    fun mapPresenter(): MapPresenter

    fun cameraPresenter(): CameraPresenter

    fun feedPresenter(): FeedPresenter

    fun trackingFeedPresenter(): TrackingFeedPresenter

    fun potholePresenter(): PotholePresenter

    fun trackPresenter(): TrackPresenter

}