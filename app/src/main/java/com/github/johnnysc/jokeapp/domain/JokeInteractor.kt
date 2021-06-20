package com.github.johnnysc.jokeapp.domain

/**
 * @author Asatryan on 19.06.2021
 **/
interface JokeInteractor {
    suspend fun getJoke(): Joke
    suspend fun changeFavorites(): Joke
    fun getFavoriteJokes(favorites: Boolean)
}