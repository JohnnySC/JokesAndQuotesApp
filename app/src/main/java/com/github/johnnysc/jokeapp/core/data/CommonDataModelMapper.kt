package com.github.johnnysc.jokeapp.core.data

/**
 * @author Asatryan on 19.06.2021
 **/
interface CommonDataModelMapper<T> {
    fun map(id: Int, first: String, second: String, cached: Boolean): T
}