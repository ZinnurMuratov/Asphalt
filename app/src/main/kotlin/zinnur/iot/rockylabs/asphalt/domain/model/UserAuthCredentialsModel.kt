package zinnur.iot.rockylabs.asphalt.domain.model

/**
 * Created by Zinnur on 04.04.17.
 */

class UserAuthCredentialsModel(val accessToken: String,
                               val refreshToken: String,
                               val name: String? = null,
                               val email: String? = null) {


    fun copyWithRefreshedtoken(refreshedToken: String): UserAuthCredentialsModel {
        return UserAuthCredentialsModel(refreshedToken, refreshToken, this.name, this.email)
    }

    fun updateWithUserData( name: String, email: String): UserAuthCredentialsModel{
        return UserAuthCredentialsModel(this.accessToken, this.refreshToken, name, email)
    }
}
