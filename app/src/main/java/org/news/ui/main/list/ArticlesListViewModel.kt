package org.news.ui.main.list

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.news.BuildConfig
import org.news.R
import org.news.data.DataManager
import org.news.data.model.Article
import javax.inject.Inject


class ArticlesListViewModel @Inject
internal constructor(private val dataManager: DataManager) : ViewModel() {

    val articlesListAdapter: ArticlesListAdapter = ArticlesListAdapter()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadArticles() }

    init {
        loadArticles()
    }

    private fun loadArticles() {
        compositeDisposable.add(dataManager.remoteApi.getArticles("us", BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribe() }
            .doOnTerminate { onTerminate() }
            .subscribe(
                { newsResponse -> displayNews(newsResponse.articles) },
                { onError() }
            ))
    }

    private fun onSubscribe() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onTerminate() {
        loadingVisibility.value = View.GONE
    }

    private fun displayNews(articles: List<Article>) {
        articlesListAdapter.updateArticlesList(articles)
    }

    private fun onError() {
        errorMessage.value = R.string.error
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}