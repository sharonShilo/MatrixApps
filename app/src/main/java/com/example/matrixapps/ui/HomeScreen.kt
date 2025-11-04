package com.example.matrixapps.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matrixapps.viewmodel.AppViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matrixapps.model.App
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(viewModel: AppViewModel = hiltViewModel()) {
    val freeApps by viewModel.topFreeApps.collectAsState()
    val paidApps by viewModel.top25PaidApps.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    LazyColumn {
        item {
            Text(
                "Top Free Apps", style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
            HorizontalAppList(
                apps = freeApps,
                favorites = favorites,
                onFavoriteClick = viewModel::toggleFavorite
            )

        }
        item {
            Text(
                "Top Paid Apps",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
            VerticalAppList(
                apps = paidApps,
                favorites = favorites,
                onFavoriteClick = viewModel::toggleFavorite
            )
        }
    }

}


@Composable
fun HorizontalAppList(apps: List<App>, favorites: Set<String>, onFavoriteClick: (App) -> Unit) {
    LazyRow {
        items(apps) { app ->
            AppCardSmall(
                app = app,
                isFavorite = favorites.contains(app.id),
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}


@Composable
fun VerticalAppList(apps: List<App>, favorites: Set<String>, onFavoriteClick: (App) -> Unit) {
    Column {
        apps.forEach { app ->
            AppCardLarge(app, favorites.contains(app.id), onFavoriteClick)
        }
    }
}
