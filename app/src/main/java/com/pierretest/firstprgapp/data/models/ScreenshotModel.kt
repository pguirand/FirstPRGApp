package com.pierretest.firstprgapp.data.models


import com.google.gson.annotations.SerializedName

data class ScreenshotModel(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: String? = ""
)