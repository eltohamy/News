package org.news

import android.app.Application
import android.content.Context
import org.news.di.component.ApplicationComponent
import org.news.di.component.DaggerApplicationComponent
import org.news.di.module.ApplicationModule
import org.news.di.module.NetworkModule

class AppControl : Application() {

    private var mApplicationComponent: ApplicationComponent? = null

    val component: ApplicationComponent?
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                    .networkModule(NetworkModule())
                    .applicationModule(ApplicationModule(this))
                    .build()
            }
            return mApplicationComponent
        }

    companion object {
        operator fun get(context: Context): AppControl {
            return context.applicationContext as AppControl
        }
    }

}