package com.github.johnnysc.jokeapp

import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.core.domain.FailureHandler
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
import retrofit2.Retrofit

/**
 * @author Asatryan on 22.07.2021
 **/
class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
) : Module.Base<Int, JokesViewModel>() {

    private var communication: BaseCommunication<Int>? = null

    override fun getCommunications(): BaseCommunication<Int> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    override fun getViewModel() = JokesViewModel(getInteractor(), getCommunications())

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCacheDataSource(), getCloudDataSource(), BaseCachedData())

    private fun getCacheDataSource() =
        JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonMapper())

    private fun getCloudDataSource() =
        JokeCloudDataSource(retrofit.create(BaseJokeService::class.java))
}