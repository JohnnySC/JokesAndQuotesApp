package com.github.johnnysc.jokeapp.core.data

/**
 * @author Asatryan on 19.06.2021
 **/
interface CommonDataModelMapper<T, E> {
    fun map(id: E, first: String, second: String, cached: Boolean): T
}