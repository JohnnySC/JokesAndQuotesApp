package com.github.johnnysc.jokeapp.core.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.ChangeCommonItem

/**
 * @author Asatryan on 19.06.2021
 **/
interface CachedData : ChangeCommonItem {
    fun save(data: CommonDataModel)
    fun clear()
}