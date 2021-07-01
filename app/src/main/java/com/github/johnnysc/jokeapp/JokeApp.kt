package com.github.johnnysc.jokeapp

import android.app.Application
import com.github.johnnysc.jokeapp.data.BaseRepository
import com.github.johnnysc.jokeapp.data.cache.BaseCachedData
import com.github.johnnysc.jokeapp.data.cache.BaseRealmProvider
import com.github.johnnysc.jokeapp.data.cache.*
import com.github.johnnysc.jokeapp.data.mapper.JokeRealmMapper
import com.github.johnnysc.jokeapp.data.CommonSuccessMapper
import com.github.johnnysc.jokeapp.data.mapper.QuoteRealmMapper
import com.github.johnnysc.jokeapp.data.net.*
import com.github.johnnysc.jokeapp.domain.BaseInteractor
import com.github.johnnysc.jokeapp.domain.FailureFactory
import com.github.johnnysc.jokeapp.presentation.BaseCommunication
import com.github.johnnysc.jokeapp.presentation.BaseResourceManager
import com.github.johnnysc.jokeapp.presentation.BaseViewModel
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Asatryan on 13.06.2021
 **/
class JokeApp : Application() {

    lateinit var viewModel: BaseViewModel<Int>
    lateinit var jokeCommunication: BaseCommunication<Int>
    lateinit var quoteViewModel: BaseViewModel<String>

    override fun onCreate() {
        super.onCreate()
        //region existing code
        Realm.init(this)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val realmProvider = BaseRealmProvider()
        val cacheDataSource =
            JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonMapper())
        val cloudDataSource = JokeCloudDataSource(retrofit.create(BaseJokeService::class.java))
        val jokeRepository = BaseRepository(cacheDataSource, cloudDataSource, BaseCachedData())
        val failureHandler = FailureFactory(BaseResourceManager(this))
        val mapper = CommonSuccessMapper<Int>()
        val interactor =
            BaseInteractor(jokeRepository, failureHandler, mapper)
        jokeCommunication = BaseCommunication()
        viewModel = BaseViewModel(interactor, jokeCommunication)
        //endregion
        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedData()
        )
        val quoteMapper = CommonSuccessMapper<String>()
        quoteViewModel = BaseViewModel(
            BaseInteractor(quoteRepository, failureHandler, quoteMapper),
            BaseCommunication()
        )
    }
}