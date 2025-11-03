package com.example.matrixapps.model

data class Feed(
    val title: String,
    val id: String,
    val author: Author,
    val links: List<Link>,
    val copyright: String,
    val country: String,
    val icon: String,
    val updated: String,
    val results: List<App>
)
