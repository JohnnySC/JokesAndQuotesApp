package com.github.johnnysc.jokeapp.data.mapper

import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.data.cache.QuoteRealmModel

/**
 * @author Asatryan on 21.06.2021
 **/
class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel> {
    override fun map(id: Int, first: String, second: String, cached: Boolean) =
        QuoteRealmModel().also { quote ->
            quote.id = id
            quote.content = first
            quote.author = second
        }
}