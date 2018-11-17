package com.bigcake.android_base.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bigcake.android_base.data.local.prefs.SharedPreferencesSource
import com.bigcake.android_base.data.local.prefs.SharedPreferencesSourceImpl
import com.bigcake.android_base.data.remote.Api
import com.bigcake.android_base.data.remote.RemoteDataSource
import com.bigcake.android_base.data.remote.RemoteDataSourceImpl
import com.bigcake.android_base.di.qualifier.ApplicationContext
import com.bigcake.android_base.di.qualifier.BodyLoggingInterceptor
import com.bigcake.android_base.di.qualifier.HeaderLoggingInterceptor
import com.bigcake.android_base.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kiethuynh on 14/09/2017
 */

@Module
class AppModule(private val application: Application) {

    @Provides
    @ApplicationScope
    @ApplicationContext
    fun provideContext() = application.applicationContext!!

    @Provides
    fun provideHttpClient(@HeaderLoggingInterceptor headerLogging: HttpLoggingInterceptor,
                          @BodyLoggingInterceptor bodyLogging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .addInterceptor(headerLogging)
                .addInterceptor(bodyLogging)
                .build()
    }

    @Provides
    @HeaderLoggingInterceptor
    fun provideHeaderLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS }
    }

    @Provides
    @BodyLoggingInterceptor
    fun provideBodyLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    fun provideRemoteDataSource(source: RemoteDataSourceImpl): RemoteDataSource {
        return source
    }

    @Provides
    fun providePrefsSource(source: SharedPreferencesSourceImpl): SharedPreferencesSource {
        return source
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("", Context.MODE_PRIVATE)
    }
}