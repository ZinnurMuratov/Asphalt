package zinnur.iot.rockylabs.asphalt

import com.bluelinelabs.conductor.Controller
import zinnur.iot.rockylabs.asphalt.di.components.ControllerComponent
import zinnur.iot.rockylabs.asphalt.di.components.DaggerControllerComponent
import zinnur.iot.rockylabs.asphalt.di.modules.ControllerModule
import zinnur.iot.rockylabs.asphalt.navigation.Navigator
import zinnur.iot.rockylabs.asphalt.navigation.PhoneNavigator

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