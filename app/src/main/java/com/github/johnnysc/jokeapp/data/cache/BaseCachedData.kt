package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.ChangeCommonItem
import com.github.johnnysc.jokeapp.core.data.ChangeStatus
import com.github.johnnysc.jokeapp.core.data.cache.CachedData

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCachedData : CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()
    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}