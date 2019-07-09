package org.news.util

import android.view.View
import org.news.ui.main.list.ArticlesViewModel

interface OnArticleClickListener {
    fun onArticleClick(view: View, viewModel: ArticlesViewModel)
}