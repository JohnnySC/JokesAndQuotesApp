package com.github.johnnysc.jokeapp.core.domain

import com.github.johnnysc.jokeapp.core.presentation.Failure

/**
 * @author Asatryan on 19.06.2021
 **/
interface FailureHandler {
    fun handle(e: Exception): Failure
}
