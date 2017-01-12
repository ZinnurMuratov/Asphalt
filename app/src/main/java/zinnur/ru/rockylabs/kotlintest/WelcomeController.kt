package zinnur.ru.rockylabs.kotlintest

import zinnur.ru.rockylabs.kotlintest.anko.WelcomeControllerLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import kotlin.ru.rockylabs.kotlintest.R
import android.widget.TextView
import android.widget.LinearLayout
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by Zinnur on 11.01.17.
 */

class WelcomeController : Controller() {

    var background: LinearLayout? = null
    var actionText: TextView? = null
    private val viewBinder = WelcomeControllerLayout()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = viewBinder.bind(this)

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        viewBinder.unbind(this)
    }




}