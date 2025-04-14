package pillihuaman.com.fibertechia.inteface

import pillihuaman.com.fibertechia.entity.response.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.Call
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/v1/auth/authenticate")
    fun authenticate(
        @Body body: Map<String, String>,
        @Header("email") email: String,
        @Header("password") password: String
    ): Call<ResponseBody<Map<String, Any>>>
}