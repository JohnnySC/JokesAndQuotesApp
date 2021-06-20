package com.github.johnnysc.jokeapp.data.mapper

import com.github.johnnysc.jokeapp.data.cache.JokeRealmModel

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeRealmMapper : JokeDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, text: String, punchline: String, cached: Boolean) =
        JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = text
            joke.punchLine = punchline
        }
}