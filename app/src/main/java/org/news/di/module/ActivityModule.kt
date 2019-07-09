package org.news.di.module


import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import org.news.di.ActivityContext

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return mActivity
    }
}
