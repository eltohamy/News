package org.news.di.component

import dagger.Subcomponent
import org.news.di.PerActivity
import org.news.di.module.ActivityModule
import org.news.ui.base.BaseFragment
import org.news.ui.main.list.ArticlesListFragment

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseFragment: BaseFragment)
    fun inject(articlesListFragment: ArticlesListFragment)

}