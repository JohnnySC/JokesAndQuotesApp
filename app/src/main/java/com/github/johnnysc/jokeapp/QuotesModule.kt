package com.github.johnnysc.jokeapp

import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.core.domain.FailureHandler
import com.github.johnnysc.jokeapp.data.BaseRepository
import com.github.johnnysc.jokeapp.data.CommonSuccessMapper
import com.github.johnnysc.jokeapp.data.cache.BaseCachedData
import com.github.johnnysc.jokeapp.data.cache.QuoteCachedDataSource
import com.github.johnnysc.jokeapp.data.cache.QuoteRealmToCommonMapper
import com.github.johnnysc.jokeapp.data.mapper.QuoteRealmMapper
import com.github.johnnysc.jokeapp.data.net.QuoteCloudDataSource
import com.github.johnnysc.jokeapp.data.net.QuoteService
import com.github.johnnysc.jokeapp.domain.BaseInteractor
import com.github.johnnysc.jokeapp.presentation.BaseCommunication
import com.github.johnnysc.jokeapp.presentation.QuotesViewModel
import retrofit2.Retrofit

/**
 * @author Asatryan on 22.07.2021
 **/
class QuotesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
) : Module.Base<String, QuotesViewModel>() {

    private var communication: BaseCommunication<String>? = null

    override fun getCommunications(): BaseCommunication<String> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    override fun getViewModel() = QuotesViewModel(getInteractor(), getCommunications())

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() = BaseRepository(
        getCacheDataSource(),
        getCloudDataSource(),
        BaseCachedData()
    )

    private fun getCacheDataSource() =
        QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper())

    private fun getCloudDataSource() =
        QuoteCloudDataSource(retrofit.create(QuoteService::class.java))
}