package com.github.johnnysc.jokeapp.presentation

import androidx.annotation.StringRes
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.core.ResourceManager
import com.github.johnnysc.jokeapp.core.presentation.Failure

/**
 * @author Asatryan on 19.06.2021
 **/
abstract class BaseFailure(private val resourceManager: ResourceManager) : Failure {

    @StringRes
    protected abstract fun getMessageResId(): Int

    override fun getMessage(): String = resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager) : BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager) : BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_unavailable
}

class NoCachedData(resourceManager: ResourceManager) : BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_data
}

class GenericError(resourceManager: ResourceManager) : BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.generic_fail_message
}