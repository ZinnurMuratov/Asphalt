package zinnur.iot.rockylabs.asphalt.presentation

import android.app.Activity
import com.bluelinelabs.conductor.Controller
import zinnur.iot.rockylabs.asphalt.presentation.di.components.ControllerComponent
import zinnur.iot.rockylabs.asphalt.presentation.di.components.DaggerControllerComponent
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.ControllerModule
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.TrackingPresenter
import zinnur.iot.rockylabs.asphalt.presentation.navigation.Navigator
import zinnur.iot.rockylabs.asphalt.presentation.navigation.PhoneNavigator
import zinnur.iot.rockylabs.asphalt.presentation.AsphaltApp

/**
 * Created by Zinnur on 11.02.17.
 */

val Controller.daggerComponent: ControllerComponent
    get() = DaggerControllerComponent
            .builder()
            .applicationComponent(AsphaltApp.getComponent(applicationContext!!))
            .controllerModule(ControllerModule(PhoneNavigator(router)))
            .build()



val Controller.navigator: Navigator
    get() = daggerComponent.navigator()







