package ir.mohsenebrahimy.goldproject.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitService {
    private const val url = "https://tools.daneshjooyar.com/api/v1/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val mainApiService: MainApiService = retrofit.create(MainApiService::class.java)
}