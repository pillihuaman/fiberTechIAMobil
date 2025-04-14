package pillihuaman.com.fibertechia.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pillihuaman.com.fibertechia.api.AuthApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://ec2-3-145-180-222.us-east-2.compute.amazonaws.com:8085"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val authApi: AuthApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AuthApi::class.java)
}
