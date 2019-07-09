package org.news.ui.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.news.R
import org.news.databinding.ArticleDetailsFragmentBinding
import org.news.ui.base.BaseFragment

class ArticleDetailsFragment : BaseFragment() {

    private lateinit var binding: ArticleDetailsFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.article_details_fragment, container, false)
        val view = binding.root
        view.isClickable = true
        view.requestFocus()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        baseActivity?.supportActionBar?.setHomeButtonEnabled(true)
        baseActivity?.supportActionBar?.title = getString(R.string.details)
        val args = ArticleDetailsFragmentArgs.fromBundle(arguments!!)
        binding.article = args.article
    }
}