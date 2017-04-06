package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.View

/**
 * Created by Zinnur on 12.01.17.
 */

interface ViewBinder<in T> {
    fun bind(ui: T) : View
    fun unbind(t: T)
}
