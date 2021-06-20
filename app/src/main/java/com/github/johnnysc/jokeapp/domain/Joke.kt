package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 15.06.2021
 **/
sealed class Joke : Mapper<JokeUiModel> {
    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ) : Joke() {
        override fun to(): JokeUiModel {
            return if (favorite) {
                FavoriteJokeUiModel(text, punchline)
            } else {
                BaseJokeUiModel(text, punchline)
            }
        }
    }
    class Failed(private val failure: JokeFailure) : Joke() {
        override fun to(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}