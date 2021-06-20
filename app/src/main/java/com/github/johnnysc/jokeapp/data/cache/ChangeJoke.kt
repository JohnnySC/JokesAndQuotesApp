package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.JokeDataModel
import java.lang.IllegalStateException

/**
 * @author Asatryan on 19.06.2021
 **/
interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel

    class Empty : ChangeJoke {
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}