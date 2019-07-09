package org.news.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.news.data.DataManager
import org.news.ui.main.list.ArticlesListViewModel


class ArticleViewModelFactory(private val dataManager: DataManager) :
    ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticlesListViewModel(dataManager) as T
    }
}