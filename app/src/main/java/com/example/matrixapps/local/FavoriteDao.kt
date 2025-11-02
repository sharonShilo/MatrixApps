package com.example.matrixapps.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.matrixapps.model.FavoriteApp
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun addFavorite(cachedFavoriteMedia: FavoriteApp)

    @Delete
    suspend fun removeFavorite(cachedFavoriteMedia: FavoriteApp)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): Flow<List<FavoriteApp>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean
}
