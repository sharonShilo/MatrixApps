package com.example.matrixapps.model

data class AppResult(
    val artistName: String,
    val id: String,
    val name: String,
    val releaseDate: String,
    val kind: String,
    val artworkUrl100: String,
    val genres: List<Genre>,
    val url: String
)
