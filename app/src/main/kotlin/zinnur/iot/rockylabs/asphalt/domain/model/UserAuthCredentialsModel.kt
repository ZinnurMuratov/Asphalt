package zinnur.iot.rockylabs.asphalt.domain.model

/**
 * Created by Zinnur on 04.04.17.
 */

class UserAuthCredentialsModel(val accessToken: String, val refreshToken: String) {


    fun copyWithRefreshedtoken(refreshedToken: String): UserAuthCredentialsModel {
        return UserAuthCredentialsModel(refreshedToken, refreshToken)
    }
}
