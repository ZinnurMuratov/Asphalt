package zinnur.iot.rockylabs.asphalt.UI.anko

import android.graphics.Typeface
import android.text.Html
import android.widget.TextView
import java.util.*

/**
 * Created by Zinnur on 11.02.17.
 */
var TextView.textColorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setTextColor(resources.getColor(value))
    }
var TextView.hintTextColorResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setHintTextColor(resources.getColor(value))
    }
var TextView.textColorsResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setTextColor(resources.getColorStateList(value))
    }
var TextView.hintTextColorsResource: Int
    get() = throw IllegalAccessException()
    set(value) {
        setHintTextColor(resources.getColorStateList(value))
    }

val fontCache: HashMap<String, Typeface> = HashMap()
fun TextView.setFont(fileName: String) {
    typeface = fontCache[fileName] ?: {
        val font = Typeface.createFromAsset(context.assets, fileName)
        fontCache[fileName] = font
        font
    }()
}

