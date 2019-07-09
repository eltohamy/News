package org.news.di.component

import dagger.Component
import org.news.data.DataManager
import org.news.di.module.ApplicationModule
import org.news.di.module.NetworkModule
import org.news.ui.base.BaseActivity
import org.news.ui.base.BaseFragment
import org.news.ui.main.list.ArticlesListViewModel
import org.news.ui.main.list.ArticlesViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ApplicationModule::class])
interface ApplicationComponent {

    fun dataManager(): DataManager
    fun inject(baseFragment: BaseFragment)
    fun inject(baseActivity: BaseActivity)

    /**
     * Injects required dependencies into the specified ArticleListViewModel.
     * @param articlesListViewModel ArticleListViewModel in which to inject the dependencies
     */
    fun inject(articlesListViewModel: ArticlesListViewModel)

    /**
     * Injects required dependencies into the specified ArticleViewModel.
     * @param articleViewModel ArticleViewModel in which to inject the dependencies
     */
    fun inject(articleViewModel: ArticlesViewModel)

}

