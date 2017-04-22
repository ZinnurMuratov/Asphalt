package zinnur.iot.rockylabs.asphalt.presentation

/**
 * Created by Zinnur on 10.02.17.
 */
import android.app.Application
import android.content.Context
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.squareup.leakcanary.LeakCanary
import zinnur.iot.rockylabs.asphalt.presentation.di.components.ApplicationComponent
import zinnur.iot.rockylabs.asphalt.presentation.di.components.DaggerApplicationComponent
import zinnur.iot.rockylabs.asphalt.presentation.di.modules.ApplicationModule

@Suppress("DEPRECATION")
/**
 * Created by Zinnur on 12.01.17.
 */

class AsphaltApp : Application() {


    companion object {

        @JvmStatic lateinit var component: ApplicationComponent


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
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        RxPaparazzo.register(this)
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }



}