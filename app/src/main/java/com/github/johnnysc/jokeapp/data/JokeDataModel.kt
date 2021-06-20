package com.github.johnnysc.jokeapp.data

import com.github.johnnysc.jokeapp.data.cache.ChangeJoke
import com.github.johnnysc.jokeapp.data.cache.ChangeJokeStatus
import com.github.johnnysc.jokeapp.data.mapper.JokeDataModelMapper

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeDataModel(
    private val id: Int,
    private val text: String,
    private val punchline: String,
    private val cached: Boolean = false
) : ChangeJoke {
    fun <T> map(mapper: JokeDataModelMapper<T>): T {
        return mapper.map(id, text, punchline, cached)
    }
    override suspend fun change(changeJokeStatus: ChangeJokeStatus) =
        changeJokeStatus.addOrRemove(id, this)
    fun changeCached(cached: Boolean) = JokeDataModel(id, text, punchline, cached)
}