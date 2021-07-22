package com.github.johnnysc.jokeapp.sl

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

/**
 * @author Asatryan on 22.07.2021
 **/
class QuotesModule(private val instancesProvider: CommonInstancesProvider) :
    Module.Base<String, QuotesViewModel>() {

    override fun getViewModel() = QuotesViewModel(getInteractor(), BaseCommunication())

    private fun getInteractor() =
        BaseInteractor(
            getRepository(),
            instancesProvider.provideFailureHandler(),
            CommonSuccessMapper()
        )

    private fun getRepository() = BaseRepository(
        getCacheDataSource(),
        getCloudDataSource(),
        BaseCachedData()
    )

    private fun getCacheDataSource() =
        QuoteCachedDataSource(instancesProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper())

    private fun getCloudDataSource() =
        QuoteCloudDataSource(instancesProvider.makeService(QuoteService::class.java))
}