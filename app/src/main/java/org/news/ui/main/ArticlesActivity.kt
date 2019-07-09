package org.news.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation.findNavController
import org.news.R
import org.news.databinding.ArticlesActivityBinding
import org.news.ui.base.BaseActivity


class ArticlesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ArticlesActivityBinding>(this, R.layout.articles_activity)
        setSupportActionBar(binding.toolbar)
    }

    override fun onSupportNavigateUp() =
        findNavController(this, R.id.nav_host_fragment).navigateUp()

}
