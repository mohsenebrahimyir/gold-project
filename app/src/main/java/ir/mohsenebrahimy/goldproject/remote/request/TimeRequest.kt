package ir.mohsenebrahimy.goldproject.remote.request

import ir.mohsenebrahimy.goldproject.remote.model.TimeModel

interface TimeRequest {

    fun onSuccess(data: TimeModel)

    fun onNotSuccess(message: String)

    fun onError(error: String)
}