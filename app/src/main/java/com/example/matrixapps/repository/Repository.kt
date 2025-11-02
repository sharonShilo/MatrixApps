package com.example.matrixapps.repository

import com.example.matrixapps.local.FavoriteDao
import com.regev.tmdbApp.network.AppleMarketService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: AppleMarketService,
    private val favoriteDao: FavoriteDao
) {


    suspend fun toggleFavorite(app: App) {
        val favorite = app.toFavoriteApp()
        if (favoriteDao.isFavorite(app.id)) {
            favoriteDao.removeFavorite(favorite)
        } else {
            favoriteDao.addFavorite(favorite)
        }
    }
}