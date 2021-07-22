package com.github.johnnysc.jokeapp

import android.app.Application
import com.github.johnnysc.jokeapp.core.data.cache.PersistentDataSource
import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.core.domain.FailureHandler
import com.github.johnnysc.jokeapp.data.cache.BaseRealmProvider
import com.github.johnnysc.jokeapp.domain.FailureFactory
import com.github.johnnysc.jokeapp.presentation.BaseResourceManager
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Asatryan on 13.06.2021
 **/
class JokesAndQuotesApp : Application() {

    val viewModelsFactory by lazy {
        ViewModelsFactory(
            MainModule(persistentDataStore),
            JokesModule(failureHandler, realmProvider, retrofit),
            QuotesModule(failureHandler, realmProvider, retrofit)
        )
    }

    private lateinit var retrofit: Retrofit
    private lateinit var realmProvider: RealmProvider
    private lateinit var failureHandler: FailureHandler
    private lateinit var persistentDataStore: PersistentDataSource

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        realmProvider = BaseRealmProvider()
        failureHandler = FailureFactory(BaseResourceManager(this))
        persistentDataStore = PersistentDataSource.Base(this)
    }
}