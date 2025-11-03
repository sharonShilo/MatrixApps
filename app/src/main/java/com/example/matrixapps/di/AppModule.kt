package com.regev.tmdbApp.di

import android.content.Context
import androidx.room.Room
import com.example.matrixapps.local.AppDatabase
import com.example.matrixapps.local.FavoriteDao
import com.example.matrixapps.repository.Repository
import com.regev.tmdbApp.network.AppleMarketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTmbdApiService(): AppleMarketService {
        return Retrofit.Builder()
            .baseUrl("https://rss.applemarketingtools.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppleMarketService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(appleMarketService: AppleMarketService, @ApplicationContext context: Context, favoriteDao: FavoriteDao): Repository {
        return Repository(appleMarketService, favoriteDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "store_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: AppDatabase) = database.favoriteDao()
}