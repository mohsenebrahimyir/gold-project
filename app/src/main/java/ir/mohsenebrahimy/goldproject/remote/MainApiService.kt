package ir.mohsenebrahimy.goldproject.remote

import ir.mohsenebrahimy.goldproject.remote.model.TimeModel
import ir.mohsenebrahimy.goldproject.remote.model.PriceModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {
    @GET("date/now")
    fun getTime(
        @Query("short") short: Boolean
    ) : Call<TimeModel>

    @GET("currencies")
    fun getPrice() : Call<PriceModel>
}