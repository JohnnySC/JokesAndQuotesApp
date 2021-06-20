package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.JokeDataModel

/**
 * @author Asatryan on 19.06.2021
 **/
interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}