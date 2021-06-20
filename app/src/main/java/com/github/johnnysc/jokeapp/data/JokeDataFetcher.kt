package com.github.johnnysc.jokeapp.data

/**
 * @author Asatryan on 19.06.2021
 **/
interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}