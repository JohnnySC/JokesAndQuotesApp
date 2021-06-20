package com.github.johnnysc.jokeapp.data.mapper

import com.github.johnnysc.jokeapp.domain.Joke

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean) =
        Joke.Success(text, punchline, cached)
}