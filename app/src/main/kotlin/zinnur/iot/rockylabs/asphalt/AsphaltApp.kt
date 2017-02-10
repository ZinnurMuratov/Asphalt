package zinnur.iot.rockylabs.asphalt

/**
 * Created by Zinnur on 10.02.17.
 */
import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import zinnur.iot.rockylabs.asphalt.di.components.ApplicationComponent
import zinnur.iot.rockylabs.asphalt.di.components.DaggerApplicationComponent
import zinnur.iot.rockylabs.asphalt.di.modules.ApplicationModule

@Suppress("DEPRECATION")
/**
 * Created by Zinnur on 12.01.17.
 */

class AsphaltApp : Application() {


    companion object {

        private var component: ApplicationComponent? = null

        fun getComponent(c: Context): ApplicationComponent {
            if (component == null) {
                component = DaggerApplicationComponent
                        .builder()
                        .applicationModule(ApplicationModule(c.applicationContext))
                        .build()
            }
            return component!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}