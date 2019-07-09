package org.news.ui.base

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.LongSparseArray
import org.news.AppControl
import org.news.di.component.ActivityComponent
import org.news.di.component.ConfigPersistentComponent
import org.news.di.component.DaggerConfigPersistentComponent
import org.news.di.module.ActivityModule
import java.util.concurrent.atomic.AtomicLong

abstract class BaseActivity : AppCompatActivity() {
    private val sComponentsMap = LongSparseArray<ConfigPersistentComponent>()

    private var mActivityComponent: ActivityComponent? = null
    private var mActivityId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        AppControl[this].component?.inject(this)
        val configPersistentComponent: ConfigPersistentComponent?
        if (!sComponentsMap.containsKey(mActivityId)) {
            Log.i("Creating new", String.format("ConfigPersistentComponent id=%d", mActivityId))
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(AppControl[this].component)
                .build()
            sComponentsMap.put(mActivityId, configPersistentComponent)
        } else {
            Log.i("Reusing", String.format("ConfigPersistentComponent id=%d", mActivityId))
            configPersistentComponent = sComponentsMap.get(mActivityId)
        }
        if (null != configPersistentComponent)
            mActivityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, mActivityId)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun activityComponent(): ActivityComponent? {
        return mActivityComponent
    }


    // --- helpers ---
    companion object {

        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
    }
}
