package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.data.mapper.JokeDataModelMapper
import com.github.johnnysc.jokeapp.data.JokeRepository

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
) : JokeInteractor {
    override suspend fun getJoke(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }
    override suspend fun changeFavorites(): Joke {
        return try {
            repository.changeJokeStatus().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }
    override fun getFavoriteJokes(favorites: Boolean) =
        repository.chooseDataSource(favorites)
}