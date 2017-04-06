package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.ViewManager
import com.jjoe64.graphview.GraphView
import org.jetbrains.anko.custom.ankoView

/**
 * Created by Zinnur on 27.02.17.
 */

inline fun ViewManager.graphView(theme: Int = 0, init: GraphView.() -> Unit) = ankoView({ GraphView(it) }, theme, init)