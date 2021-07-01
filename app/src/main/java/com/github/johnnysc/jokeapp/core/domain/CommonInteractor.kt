package com.github.johnnysc.jokeapp.core.domain

import com.github.johnnysc.jokeapp.domain.CommonItem

/**
 * @author Asatryan on 19.06.2021
 **/
interface CommonInteractor<T> {
    suspend fun getItem(): CommonItem<T>
    suspend fun getItemList() : List<CommonItem<T>>
    suspend fun changeFavorites(): CommonItem<T>
    fun getFavorites(favorites: Boolean)
    suspend fun removeItem(id: T)
}