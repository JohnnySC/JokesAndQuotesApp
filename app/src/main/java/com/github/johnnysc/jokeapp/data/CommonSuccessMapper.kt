package com.github.johnnysc.jokeapp.data

import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.domain.CommonItem

/**
 * @author Asatryan on 19.06.2021
 **/
class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, first: String, second: String, cached: Boolean) =
        CommonItem.Success(first, second, cached)
}