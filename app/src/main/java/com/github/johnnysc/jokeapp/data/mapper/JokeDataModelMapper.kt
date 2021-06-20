package com.github.johnnysc.jokeapp.data.mapper

/**
 * @author Asatryan on 19.06.2021
 **/
interface JokeDataModelMapper<T> {
    fun map(id: Int, text: String, punchline: String, cached: Boolean): T
}