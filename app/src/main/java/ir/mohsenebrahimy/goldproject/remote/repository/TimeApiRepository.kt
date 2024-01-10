package ir.mohsenebrahimy.goldproject.remote.repository

import ir.mohsenebrahimy.goldproject.remote.MainRetrofitService
import ir.mohsenebrahimy.goldproject.remote.model.TimeModel
import ir.mohsenebrahimy.goldproject.remote.request.TimeRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeApiRepository private constructor() {

    companion object {
        private var apiRepository: TimeApiRepository? = null

        val instance: TimeApiRepository
            get() {
                if (apiRepository == null) apiRepository = TimeApiRepository()
                return apiRepository!!
            }
    }

    fun getTime(
        request: TimeRequest
    ) {
        MainRetrofitService.mainApiService.getTime(true).enqueue(
            object : Callback<TimeModel> {
                override fun onResponse(call: Call<TimeModel>, response: Response<TimeModel>) {
                    if (response.isSuccessful) {
                        val data = response.body() as TimeModel
                        request.onSuccess(data)
                    } else {
                        request.onNotSuccess("Not Success")
                    }
                }

                override fun onFailure(call: Call<TimeModel>, t: Throwable) {
                    request.onError("Error : ${t.message}")
                }

            }
        )
    }
}