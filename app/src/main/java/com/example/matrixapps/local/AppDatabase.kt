package com.example.matrixapps.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.matrixapps.model.FavoriteApp

@Database(entities = [FavoriteApp::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}