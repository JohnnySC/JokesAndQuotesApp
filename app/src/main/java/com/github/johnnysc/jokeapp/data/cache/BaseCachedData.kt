package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.ChangeCommonItem
import com.github.johnnysc.jokeapp.core.data.ChangeStatus
import com.github.johnnysc.jokeapp.core.data.cache.CachedData

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCachedData<E> : CachedData<E>{
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}