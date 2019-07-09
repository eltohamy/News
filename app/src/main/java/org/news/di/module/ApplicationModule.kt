package org.news.di.module


import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.news.di.ApplicationContext

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }
}
