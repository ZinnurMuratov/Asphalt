package zinnur.iot.rockylabs.asphalt.di.components

import com.squareup.picasso.Picasso
import dagger.Component
import zinnur.iot.rockylabs.asphalt.AsphaltApp
import zinnur.iot.rockylabs.asphalt.di.modules.ApplicationModule
import javax.inject.Singleton

/**
 * Created by Zinnur on 12.01.17.
 */

@Component(modules = arrayOf(ApplicationModule::class))
@Singleton
interface ApplicationComponent {

    fun picasso(): Picasso

    fun inject(app: AsphaltApp)
}