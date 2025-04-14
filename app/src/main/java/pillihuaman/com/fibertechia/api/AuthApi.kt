package pillihuaman.com.fibertechia.api

import pillihuaman.com.fibertechia.entity.User
import pillihuaman.com.fibertechia.entity.response.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/api/v1/auth/authenticate")
    suspend fun authenticate(@Body credentials: Map<String, String>): ResponseBody<User>
}
