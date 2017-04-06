package zinnur.iot.rockylabs.asphalt.presentation.utils

import android.text.TextUtils



/**
 * Created by Zinnur on 14.02.17.
 */


fun isEmailValid(email: String?): Boolean {
    return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
