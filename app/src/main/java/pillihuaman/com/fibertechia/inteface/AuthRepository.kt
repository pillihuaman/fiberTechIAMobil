package pillihuaman.com.fibertechia.inteface

import pillihuaman.com.fibertechia.api.AuthApi
import pillihuaman.com.fibertechia.entity.User

class AuthRepository(private val api: AuthApi) {
    suspend fun login(email: String, password: String): User? {
        val credentials = mapOf("email" to email, "password" to password)
        val response = api.authenticate(credentials)
        return response.payload
    }
}
