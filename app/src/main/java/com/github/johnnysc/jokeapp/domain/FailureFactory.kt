package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.core.ResourceManager
import com.github.johnnysc.jokeapp.core.domain.FailureHandler
import com.github.johnnysc.jokeapp.core.domain.NoCachedDataException
import com.github.johnnysc.jokeapp.core.domain.NoConnectionException
import com.github.johnnysc.jokeapp.core.domain.ServiceUnavailableException
import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 19.06.2021
 **/
class FailureFactory(private val resourceManager: ResourceManager) : FailureHandler {
    override fun handle(e: Exception) = when (e) {
        is NoConnectionException -> NoConnection(resourceManager)
        is NoCachedDataException -> NoCachedData(resourceManager)
        is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
        else -> GenericError(resourceManager)
    }
}