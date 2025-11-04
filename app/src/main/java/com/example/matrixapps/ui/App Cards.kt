package com.example.matrixapps.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.matrixapps.model.App

@Composable
fun AppCardSmall(app: App, isFavorite: Boolean, onFavoriteClick: (App) -> Unit) {
    Card(modifier = Modifier.padding(8.dp).width(120.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = app.artworkUrl100,
                contentDescription = app.name,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Text(app.name, maxLines = 1, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.labelSmall)
            IconButton(onClick = { onFavoriteClick(app)}, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else LocalContentColor.current
                )
            }
        }
    }
}

@Composable
fun AppCardLarge(app: App, isFavorite: Boolean, onFavoriteClick: (App) -> Unit) {
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = app.artworkUrl100,
                contentDescription = null,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                Text(app.name, style = MaterialTheme.typography.titleMedium)
                Text(app.artistName, style = MaterialTheme.typography.bodySmall)
                Text(app.genres.joinToString(", ") { it.name }, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onFavoriteClick(app) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else LocalContentColor.current
                )
            }
        }
    }
}