package org.news.ui.main.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.news.data.model.Article

class ArticlesViewModel : ViewModel() {
    private val title = MutableLiveData<String>()
    private lateinit var article: Article

    fun bind(articles: Article) {
        this.article = articles
        this.title.value = article.title
    }

    fun getArticleTitle(): MutableLiveData<String> {
        return title
    }

    fun getArticle(): Article {
        return article
    }
}