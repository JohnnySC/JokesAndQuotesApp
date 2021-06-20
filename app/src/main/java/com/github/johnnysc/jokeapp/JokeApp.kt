package com.github.johnnysc.jokeapp

import android.app.Application
import com.github.johnnysc.jokeapp.data.*
import com.github.johnnysc.jokeapp.data.cache.BaseCachedDataSource
import com.github.johnnysc.jokeapp.data.cache.BaseCachedJoke
import com.github.johnnysc.jokeapp.data.cache.BaseRealmProvider
import com.github.johnnysc.jokeapp.data.mapper.JokeRealmMapper
import com.github.johnnysc.jokeapp.data.mapper.JokeSuccessMapper
import com.github.johnnysc.jokeapp.data.net.*
import com.github.johnnysc.jokeapp.domain.BaseJokeInteractor
import com.github.johnnysc.jokeapp.domain.JokeFailureFactory
import com.github.johnnysc.jokeapp.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Asatryan on 13.06.2021
 **/
class JokeApp : Application() {

    lateinit var viewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cacheDataSource = BaseCachedDataSource(BaseRealmProvider(), JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor =
            BaseJokeInteractor(repository, JokeFailureFactory(resourceManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}