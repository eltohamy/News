package org.news.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.news.R
import org.news.data.model.Article
import org.news.databinding.ItemArticleBinding
import org.news.util.OnArticleClickListener

class ArticlesListAdapter : RecyclerView.Adapter<ArticlesListAdapter.ViewHolder>() {
    private lateinit var articles: List<Article>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemArticleBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_article, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return if (::articles.isInitialized) articles.size else 0
    }

    fun updateArticlesList(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root),
        OnArticleClickListener {
        private val viewModel = ArticlesViewModel()

        fun bind(article: Article) {
            viewModel.bind(article)
            binding.viewModel = viewModel
            binding.listener = this
        }

        override fun onArticleClick(view: View, viewModel: ArticlesViewModel) {
            val directions =
                ArticlesListFragmentDirections.actionMainFragmentToArticlesListFragment(viewModel.getArticle())
            view.findNavController().navigate(directions)
        }
    }
}