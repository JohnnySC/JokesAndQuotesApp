package com.github.johnnysc.jokeapp.data

/**
 * @author Asatryan on 13.06.2021
 **/
interface JokeRepository {
    suspend fun getJoke(): JokeDataModel
    suspend fun changeJokeStatus(): JokeDataModel
    fun chooseDataSource(cached: Boolean)
}