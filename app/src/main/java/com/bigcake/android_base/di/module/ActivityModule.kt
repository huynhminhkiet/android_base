package com.bigcake.android_base.di.module

import android.app.Activity
import android.content.Context
import com.bigcake.android_base.di.qualifier.ActivityContext
import com.bigcake.android_base.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by kiethuynh on 14/09/2017
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(): Context = activity

    @Provides
    @ActivityScope
    fun provideActivity() = activity
}