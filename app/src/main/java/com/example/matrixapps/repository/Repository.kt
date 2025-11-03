package com.example.matrixapps.repository

import com.example.matrixapps.local.FavoriteDao
import com.example.matrixapps.model.App
import com.example.matrixapps.model.FavoriteApp
import com.example.matrixapps.model.Genre
import com.regev.tmdbApp.network.AppleMarketService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: AppleMarketService,
    private val favoriteDao: FavoriteDao
) {


    suspend fun getTopFreeApps() = apiService.getTopFreeApps()
    suspend fun getTop25PaidApps() = apiService.getTop25PaidApps()

    suspend fun getFavorites() = favoriteDao.getFavorites()

    suspend fun isFavorite(id: String) = favoriteDao.isFavorite(id)




    suspend fun toggleFavorite(app: App) {
        val favoriteApp = convertAppToFavorite(app)
        if (favoriteDao.isFavorite(app.id)) {
            favoriteDao.removeFavorite(favoriteApp)
        } else {
            favoriteDao.addFavorite(favoriteApp)
        }
    }

    fun convertAppToFavorite(app: App): FavoriteApp {
        return FavoriteApp(
            id = app.id,
            name = app.name,
            artistName = app.artistName,
            artworkUrl = app.artworkUrl,
            releaseDate = app.releaseDate,
            genreNames = app.genres.joinToString(", ") { it.name }
        )
    }

    fun convertFavoriteAppToApp(favoriteApp: FavoriteApp): App {
        return App(
            id = favoriteApp.id,
            name = favoriteApp.name,
            artistName = favoriteApp.artistName,
            artworkUrl = favoriteApp.artworkUrl,
            releaseDate = favoriteApp.releaseDate,
            genres = favoriteApp.genreNames.split(", ").map { Genre("", it, "") },
            url = "https://apps.apple.com/us/app/id$favoriteApp.id",
        )
    }
}