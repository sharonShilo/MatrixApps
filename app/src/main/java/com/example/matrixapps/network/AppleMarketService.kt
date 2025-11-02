package com.regev.tmdbApp.network

import com.example.matrixapps.model.FeedResponse
import retrofit2.http.GET

interface AppleMarketService {
    @GET("/api/v2/us/apps/top-free/10/apps.json")
    suspend fun getTopFreeApps(
    ): FeedResponse

    @GET("/api/v2/us/apps/top-paid/25/apps.json")
    suspend fun getTop25PaidApps(
    ): FeedResponse



}