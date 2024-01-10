package ir.mohsenebrahimy.goldproject.remote.request

import ir.mohsenebrahimy.goldproject.remote.model.PriceModel

interface PriceRequest {

    fun onSuccess(data: PriceModel)

    fun onNotSuccess(message: String)

    fun onError(error: String)
}