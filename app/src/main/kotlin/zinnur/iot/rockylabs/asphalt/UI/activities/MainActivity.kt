package zinnur.iot.rockylabs.asphalt.UI.activities

import zinnur.iot.rockylabs.asphalt.UI.anko.MainActivityLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import zinnur.iot.rockylabs.asphalt.UI.controllers.WelcomeController
import zinnur.iot.rockylabs.asphalt.mvp.views.MainView

class MainActivity : AppCompatActivity(), MainView {



    lateinit var toolbar: Toolbar
    lateinit var container: ViewGroup
    private lateinit var router: Router

    private val viewBinder = MainActivityLayout()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinder.bind(this))
        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(WelcomeController()))
        }

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun showToolbar(show: Boolean) {
        if (show) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }
}
