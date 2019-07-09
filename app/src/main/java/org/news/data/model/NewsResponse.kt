package org.news.data.model

class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)