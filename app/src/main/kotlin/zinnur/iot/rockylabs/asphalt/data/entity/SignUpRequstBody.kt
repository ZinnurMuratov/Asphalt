package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 04.04.17.
 */
class SignUpRequstBody{

    @SerializedName("email") private var email: String
    @SerializedName("name") private var name: String
    @SerializedName("password") private var password: String


    constructor(email: String, name: String, password: String) {
        this.email = email
        this.name = name
        this.password = password
    }

    fun getName(): String {
        return name
    }


    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }
}