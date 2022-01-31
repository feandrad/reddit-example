package io.felipeandrade.reddit

import android.app.Application
import io.felipeandrade.reddit.domain.di.domainModule
import io.felipeandrade.reddit.domain.di.uiModule
import io.felipeandrade.reddit.domain.di.dataModule
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
            modules(listOf(dataModule, domainModule, uiModule))
        }
    }
}