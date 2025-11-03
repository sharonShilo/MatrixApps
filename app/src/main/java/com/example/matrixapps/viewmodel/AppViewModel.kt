package com.example.matrixapps.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matrixapps.model.App
import com.example.matrixapps.model.FavoriteApp
import com.example.matrixapps.model.Genre
import com.example.matrixapps.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor( private val repository: Repository) : ViewModel() {
    private val _topFreeApps = MutableStateFlow<List<App>>(emptyList())
    val topFreeApps: StateFlow<List<App>> = _topFreeApps

    private val _top25PaidApps = MutableStateFlow<List<App>>(emptyList())
    val top25PaidApps: StateFlow<List<App>> = _top25PaidApps

    private val _favorites = MutableStateFlow<Set<String>>(emptySet())
    val favorites: StateFlow<Set<String>> = _favorites

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        getData()
        listenToFavorite()
    }

    fun getData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val topFreeApps = repository.getTopFreeApps()
                val top25PaidApps = repository.getTop25PaidApps()
                _topFreeApps.value = topFreeApps.feed.results
                _top25PaidApps.value = top25PaidApps.feed.results
            } catch (e: Exception) {
                Log.e("AppViewModel", "Error fetching data: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleFavorite(app: App) {
        viewModelScope.launch {
            repository.toggleFavorite(app)
        }
    }

    private fun listenToFavorite() {
        viewModelScope.launch {
            repository.getFavorites().collect { favoritesFromDb ->
                _favorites.value = favoritesFromDb.map { it.id }.toSet()
            }
        }
    }







}