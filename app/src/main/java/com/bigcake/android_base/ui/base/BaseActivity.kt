package com.bigcake.android_base.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigcake.android_base.MyApplication
import com.bigcake.android_base.di.component.ActivityComponent
import com.bigcake.android_base.di.component.DaggerActivityComponent
import com.bigcake.android_base.di.module.ActivityModule

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
                .appComponent((application as MyApplication).getAppComponent())
                .activityModule(ActivityModule(this))
                .build()
        setUpComponent(activityComponent)
    }

    abstract fun setUpComponent(activityComponent: ActivityComponent)
}