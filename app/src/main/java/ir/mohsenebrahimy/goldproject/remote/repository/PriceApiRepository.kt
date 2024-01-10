package ir.mohsenebrahimy.goldproject.remote.repository

import ir.mohsenebrahimy.goldproject.remote.MainRetrofitService
import ir.mohsenebrahimy.goldproject.remote.model.PriceModel
import ir.mohsenebrahimy.goldproject.remote.request.PriceRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceApiRepository private constructor() {

    companion object {
        private var apiRepository: PriceApiRepository? = null

        val instance: PriceApiRepository
            get() {
                if (apiRepository == null) apiRepository = PriceApiRepository()
                return apiRepository!!
            }
    }

    fun getPrice(
        request: PriceRequest
    ) {
        MainRetrofitService.mainApiService.getPrice().enqueue(
            object : Callback<PriceModel> {
                override fun onResponse(call: Call<PriceModel>, response: Response<PriceModel>) {
                    if (response.isSuccessful) {
                        val data = response.body() as PriceModel
                        request.onSuccess(data)
                    } else {
                        request.onNotSuccess("Not Success")
                    }
                }

                override fun onFailure(call: Call<PriceModel>, t: Throwable) {
                    request.onError("Error : ${t.message}")
                }

            }
        )
    }
}