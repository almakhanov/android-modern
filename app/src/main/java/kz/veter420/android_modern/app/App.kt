package kz.veter420.android_modern.app

import android.app.Application
import kz.veter420.android_modern.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		GlobalContext.startKoin {
			androidContext(this@App)
			modules(appModule)
		}
	}
}