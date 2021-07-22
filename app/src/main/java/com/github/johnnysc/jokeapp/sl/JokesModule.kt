package com.github.johnnysc.jokeapp.sl

import com.github.johnnysc.jokeapp.data.BaseRepository
import com.github.johnnysc.jokeapp.data.CommonSuccessMapper
import com.github.johnnysc.jokeapp.data.cache.BaseCachedData
import com.github.johnnysc.jokeapp.data.cache.JokeCachedDataSource
import com.github.johnnysc.jokeapp.data.cache.JokeRealmToCommonMapper
import com.github.johnnysc.jokeapp.data.mapper.JokeRealmMapper
import com.github.johnnysc.jokeapp.data.net.BaseJokeService
import com.github.johnnysc.jokeapp.data.net.JokeCloudDataSource
import com.github.johnnysc.jokeapp.domain.BaseInteractor
import com.github.johnnysc.jokeapp.presentation.BaseCommunication
import com.github.johnnysc.jokeapp.presentation.JokesViewModel

/**
 * @author Asatryan on 22.07.2021
 **/
class JokesModule(
    private val instancesProvider: CommonInstancesProvider
) : Module.Base<Int, JokesViewModel>() {

    override fun getViewModel() = JokesViewModel(getInteractor(), BaseCommunication())

    private fun getInteractor() =
        BaseInteractor(
            getRepository(),
            instancesProvider.provideFailureHandler(),
            CommonSuccessMapper()
        )

    private fun getRepository() =
        BaseRepository(getCacheDataSource(), getCloudDataSource(), BaseCachedData())

    private fun getCacheDataSource() =
        JokeCachedDataSource(instancesProvider, JokeRealmMapper(), JokeRealmToCommonMapper())

    private fun getCloudDataSource() =
        JokeCloudDataSource(instancesProvider.makeService(BaseJokeService::class.java))
}