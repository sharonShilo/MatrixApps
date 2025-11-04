package com.example.matrixapps.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteApp(
    @PrimaryKey val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl100: String,
    val releaseDate: String,
    val genreNames: String
)
