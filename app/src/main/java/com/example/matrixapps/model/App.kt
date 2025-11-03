package com.example.matrixapps.model

data class App(
    val artistName: String,
    val id: String,
    val name: String,
    val releaseDate: String,
    val artworkUrl: String,
    val genres: List<Genre>,
    val url: String
)
