package com.github.johnnysc.jokeapp.core.data

import com.github.johnnysc.jokeapp.data.CommonDataModel

/**
 * @author Asatryan on 13.06.2021
 **/
interface CommonRepository {
    suspend fun getCommonItem(): CommonDataModel
    suspend fun changeStatus(): CommonDataModel
    fun chooseDataSource(cached: Boolean)
}