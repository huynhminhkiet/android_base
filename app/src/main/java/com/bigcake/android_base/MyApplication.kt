package com.bigcake.android_base

import android.app.Application
import com.bigcake.android_base.di.component.AppComponent
import com.bigcake.android_base.di.component.DaggerAppComponent
import com.bigcake.android_base.di.module.AppModule

class MyApplication : Application() {
  private lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()

    appComponent = DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .build()
  }

  fun getAppComponent() = appComponent
}