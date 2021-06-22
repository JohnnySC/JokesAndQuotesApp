package com.github.johnnysc.jokeapp.core.data

import com.github.johnnysc.jokeapp.data.CommonDataModel

/**
 * @author Asatryan on 13.06.2021
 **/
interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun changeStatus(): CommonDataModel<E>
    fun chooseDataSource(cached: Boolean)
}