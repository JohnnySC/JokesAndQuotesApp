package com.github.johnnysc.jokeapp.core.domain

import com.github.johnnysc.jokeapp.domain.CommonItem

/**
 * @author Asatryan on 19.06.2021
 **/
interface CommonInteractor {
    suspend fun getItem(): CommonItem
    suspend fun changeFavorites(): CommonItem
    fun getFavorites(favorites: Boolean)
}