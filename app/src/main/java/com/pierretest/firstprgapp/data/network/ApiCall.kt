package com.pierretest.firstprgapp.data.network

import com.pierretest.firstprgapp.data.models.SingleDetailGameModel
import com.pierretest.firstprgapp.data.models.SingleGameModel
import com.pierretest.firstprgapp.utils.ApiDetails
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.Category

interface ApiCall {

    @GET(ApiDetails.ALL_GAMES_ENDPOINT)
    suspend fun getAllGames() : List<SingleGameModel>

    @GET(ApiDetails.CATEGORY_ENDPOINT)
    suspend fun getGameByCategory(@Query("category") category: String) : List<SingleGameModel>

    @GET(ApiDetails.SINGLE_GAME_ENDPOINT)
    suspend fun getGameById(@Query("id") gameId:Int) : SingleDetailGameModel

    @GET(ApiDetails.PLATFORM_ENDPOINT)
    suspend fun getGameByPlatform(@Query("platform") plateform:String) : List<SingleGameModel>
}