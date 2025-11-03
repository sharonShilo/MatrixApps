package com.example.matrixapps.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matrixapps.viewmodel.AppViewModel

@Composable
fun FavoritesScreen(viewModel: AppViewModel = hiltViewModel()) {
    val favorites by viewModel.favorites.collectAsState()


    val favoriteApps = remember(favorites) {
        viewModel.topFreeApps.value + viewModel.top25PaidApps.value
            .filter { favorites.contains(it.id) }
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(8.dp)) {
        items(favoriteApps) { app ->
            AppCardSmall(app = app, isFavorite = true, onFavoriteClick = viewModel::toggleFavorite)
    }

    }

}