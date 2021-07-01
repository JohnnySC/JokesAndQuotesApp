package com.github.johnnysc.jokeapp.data

import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.domain.CommonItem

/**
 * @author Asatryan on 19.06.2021
 **/
class CommonSuccessMapper<E> : CommonDataModelMapper<CommonItem.Success<E>, E> {
    override fun map(id: E, first: String, second: String, cached: Boolean) =
        CommonItem.Success(id, first, second, cached)
}