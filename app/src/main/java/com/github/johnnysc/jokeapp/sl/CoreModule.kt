package com.github.johnnysc.jokeapp.sl

import android.content.Context
import com.github.johnnysc.jokeapp.core.data.cache.PersistentDataSource
import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.core.domain.FailureHandler
import com.github.johnnysc.jokeapp.data.cache.BaseRealmProvider
import com.github.johnnysc.jokeapp.domain.FailureFactory
import com.github.johnnysc.jokeapp.presentation.BaseResourceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Asatryan on 22.07.2021
 **/
class CoreModule(context: Context, useMocks: Boolean) : CommonInstancesProvider {

    private val realmProvider by lazy { BaseRealmProvider(context, useMocks) }
    private val persistentDataStore by lazy { PersistentDataSource.Base(context) }
    private val failureHandler by lazy { FailureFactory(BaseResourceManager(context)) }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun <T> makeService(service: Class<T>): T = retrofit.create(service)
    override fun provide() = realmProvider.provide()
    override fun providePersistentDataSource() = persistentDataStore
    override fun provideFailureHandler() = failureHandler
}

interface CommonInstancesProvider : RealmProvider {
    fun <T> makeService(service: Class<T>): T
    fun providePersistentDataSource(): PersistentDataSource
    fun provideFailureHandler(): FailureHandler
}