package zinnur.iot.rockylabs.asphalt.UI.anko

import android.support.v7.widget.RecyclerView
import android.view.ViewManager
import com.rengwuxian.materialedittext.MaterialEditText
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.editText

/**
 * Created by Zinnur on 13.02.17.
 */
inline fun ViewManager.materialEditText(theme: Int = 0, init: MaterialEditText.() -> Unit) = ankoView({ MaterialEditText(it) }, theme, init)