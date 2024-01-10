package ir.mohsenebrahimy.goldproject.remote.model

import com.google.gson.annotations.SerializedName

data class TimeModel(
    val success: Int,
    val message: String,
    val help: String,
    val date: Date
)

data class Date(
    @SerializedName("F") val monthName: String,
    @SerializedName("Y") val year: String,
    @SerializedName("j") val day: String,
    @SerializedName("l") val dayName: String
)