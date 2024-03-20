package com.example.newsiuuu.models

data class NewsResponse(
    val articles: MutableList<Article?>,
    val status: String?,
    val totalResults: Int?
)