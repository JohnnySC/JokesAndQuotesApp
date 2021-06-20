package com.github.johnnysc.jokeapp.data.net

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.data.JokeDataModel
import com.github.johnnysc.jokeapp.domain.NoConnectionException
import com.github.johnnysc.jokeapp.domain.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()
}

class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel() = service.getJoke()
}

abstract class BaseCloudDataSource<T : Mapper<JokeDataModel>> : CloudDataSource {

    protected abstract fun getJokeServerModel(): Call<T>

    override suspend fun getJoke(): JokeDataModel {
        try {
            return getJokeServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}