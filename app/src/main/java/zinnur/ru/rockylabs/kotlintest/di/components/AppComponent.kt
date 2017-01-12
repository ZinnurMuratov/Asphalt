package zinnur.ru.rockylabs.kotlintest.di.components

import zinnur.ru.rockylabs.kotlintest.MainActivity
import zinnur.ru.rockylabs.kotlintest.di.modules.ApiModule
import zinnur.ru.rockylabs.kotlintest.di.scopes.UserScope
import dagger.Component

/**
 * Created by Zinnur on 12.01.17.
 */

@UserScope
@Component(dependencies = arrayOf(NetComponent::class), modules = arrayOf(ApiModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)

}