package org.aj.gymbuddy

import android.app.Application
import org.aj.gymbuddy.db.dbModule
import org.aj.gymbuddy.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dbModule, uiModule)
        }
    }
}
