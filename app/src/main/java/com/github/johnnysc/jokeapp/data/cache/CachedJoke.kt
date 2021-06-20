package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.JokeDataModel

/**
 * @author Asatryan on 19.06.2021
 **/
interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}