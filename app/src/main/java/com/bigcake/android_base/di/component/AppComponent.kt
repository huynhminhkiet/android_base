package com.bigcake.android_base.di.component

import com.bigcake.android_base.di.module.AppModule
import com.bigcake.android_base.di.scope.ApplicationScope
import dagger.Component

/**
 * Created by kiethuynh on 14/09/2017.
 */

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {
}