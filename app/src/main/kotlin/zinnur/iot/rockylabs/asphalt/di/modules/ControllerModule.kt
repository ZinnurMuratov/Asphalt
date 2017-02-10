package zinnur.iot.rockylabs.asphalt.di.modules

import dagger.Module
import dagger.Provides
import zinnur.iot.rockylabs.asphalt.di.scopes.ControllerScope
import zinnur.iot.rockylabs.asphalt.navigation.Navigator

/**
 * Created by Zinnur on 10.02.17.
 */
@Module
class ControllerModule(private val navigator: Navigator){

    @Provides
    @ControllerScope
    fun provideNavigator() = navigator
}