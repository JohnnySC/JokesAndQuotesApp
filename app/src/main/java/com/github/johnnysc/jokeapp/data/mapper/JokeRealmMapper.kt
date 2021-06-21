package com.github.johnnysc.jokeapp.data.mapper

import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.data.cache.JokeRealmModel

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeRealmMapper : CommonDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, first: String, second: String, cached: Boolean) =
        JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = first
            joke.punchLine = second
        }
}