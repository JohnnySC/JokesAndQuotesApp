package com.github.johnnysc.jokeapp.presentation

import androidx.annotation.StringRes
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.ResourceManager

/**
 * @author Asatryan on 19.06.2021
 **/
interface JokeFailure {
    fun getMessage(): String
}

abstract class BaseJokeFailure(private val resourceManager: ResourceManager) : JokeFailure {

    @StringRes
    protected abstract fun  getMessageResId(): Int

    override fun getMessage(): String = resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_unavailable
}

class NoCachedJokes(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_jokes
}

class GenericError(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.generic_fail_message
}