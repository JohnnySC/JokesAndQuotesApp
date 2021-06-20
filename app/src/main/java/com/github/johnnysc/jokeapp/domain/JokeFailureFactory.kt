package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.ResourceManager
import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeFailureFactory(private val resourceManager: ResourceManager) : JokeFailureHandler {
    override fun handle(e: Exception) = when (e) {
        is NoConnectionException -> NoConnection(resourceManager)
        is NoCachedJokesException -> NoCachedJokes(resourceManager)
        is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
        else -> GenericError(resourceManager)
    }
}