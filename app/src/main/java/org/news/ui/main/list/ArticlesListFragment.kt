package org.news.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.news.R
import org.news.databinding.ArticlesListFragmentBinding
import org.news.ui.base.BaseFragment
import org.news.util.ArticleViewModelFactory


class ArticlesListFragment : BaseFragment() {
    private lateinit var viewModel: ArticlesListViewModel
    private lateinit var binding: ArticlesListFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.articles_list_fragment, container, false)
        val view = binding.root
        view.isClickable = true
        view.requestFocus()
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        baseActivity!!.activityComponent()!!.inject(this)
        baseActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        baseActivity?.supportActionBar?.setHomeButtonEnabled(false)
        baseActivity?.supportActionBar?.title = getString(R.string.app_name)
        viewModel =
            ViewModelProviders.of(this, ArticleViewModelFactory(dataManager)).get(ArticlesListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

    }

    private fun showError(@StringRes errorMessage: Int) {
        binding.textError.setText(errorMessage)
        binding.group.visibility = View.VISIBLE
    }

    private fun hideError() {
        binding.group.visibility = View.GONE
    }


}
