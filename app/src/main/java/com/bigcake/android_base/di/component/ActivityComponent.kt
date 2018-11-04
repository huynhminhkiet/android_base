package com.bigcake.android_base.di.component

import com.bigcake.android_base.di.module.ActivityModule
import com.bigcake.android_base.di.scope.ActivityScope
import dagger.Component

/**
 * Created by kiethuynh on 14/09/2017
 */
@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [AppComponent::class])
interface ActivityComponent {
}