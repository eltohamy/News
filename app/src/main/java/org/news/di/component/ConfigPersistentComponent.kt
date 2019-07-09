package org.news.di.component


import dagger.Component
import org.news.di.ConfigPersistent
import org.news.di.module.ActivityModule

@ConfigPersistent
@Component(dependencies = [ApplicationComponent::class])
interface ConfigPersistentComponent {
    fun activityComponent(activityModule: ActivityModule): ActivityComponent
}
