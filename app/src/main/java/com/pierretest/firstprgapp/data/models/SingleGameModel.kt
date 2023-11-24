package com.pierretest.firstprgapp.data.models


import com.google.gson.annotations.SerializedName

data class SingleGameModel(
    @SerializedName("developer")
    val developer: String? = "",
    @SerializedName("freetogame_profile_url")
    val freetogameProfileUrl: String? = "",
    @SerializedName("game_url")
    val gameUrl: String? = "",
    @SerializedName("genre")
    val genre: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("platform")
    val platform: String? = "",
    @SerializedName("publisher")
    val publisher: String? = "",
    @SerializedName("release_date")
    val releaseDate: String? = "",
    @SerializedName("short_description")
    val shortDescription: String? = "",
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("title")
    val title: String? = ""
)