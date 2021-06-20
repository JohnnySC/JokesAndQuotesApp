package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 19.06.2021
 **/
interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}
