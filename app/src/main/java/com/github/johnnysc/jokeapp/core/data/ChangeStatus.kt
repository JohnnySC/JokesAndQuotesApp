package com.github.johnnysc.jokeapp.core.data

import com.github.johnnysc.jokeapp.data.CommonDataModel

/**
 * @author Asatryan on 19.06.2021
 **/
interface ChangeStatus {
    suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel
}