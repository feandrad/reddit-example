package io.felipeandrade.reddit

import android.app.Application
import io.felipeandrade.reddit.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RedditApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    private fun initDependencyInjection() {
        startKoin {
            androidContext(this@RedditApp)
            modules(listOf(coreModule))
        }
    }
}