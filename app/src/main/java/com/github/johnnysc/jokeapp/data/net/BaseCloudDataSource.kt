package com.github.johnnysc.jokeapp.data.net

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.core.data.net.CloudDataSource
import com.github.johnnysc.jokeapp.core.domain.NoConnectionException
import com.github.johnnysc.jokeapp.core.domain.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

/**
 * @author Asatryan on 21.06.2021
 **/
abstract class BaseCloudDataSource<T : Mapper<CommonDataModel<E>>, E> : CloudDataSource<E> {
    protected abstract fun getServerModel(): Call<T>
    override suspend fun getData(): CommonDataModel<E> {
        try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}