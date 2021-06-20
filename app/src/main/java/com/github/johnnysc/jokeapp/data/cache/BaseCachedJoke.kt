package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.JokeDataModel

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCachedJoke : CachedJoke {

    private var cached: ChangeJoke = ChangeJoke.Empty()

    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = ChangeJoke.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return cached.change(changeJokeStatus)
    }
}