package zinnur.iot.rockylabs.asphalt.data.entity.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Zinnur on 04.04.17.
 */
class SignUpRequstBody{

    @com.google.gson.annotations.SerializedName("email") private var email: String
    @com.google.gson.annotations.SerializedName("name") private var name: String
    @com.google.gson.annotations.SerializedName("password") private var password: String


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