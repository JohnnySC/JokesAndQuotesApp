package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.cache.RealmToCommonDataMapper

/**
 * @author Asatryan on 21.06.2021
 **/
class QuoteRealmToCommonMapper : RealmToCommonDataMapper<QuoteRealmModel, String> {
    override fun map(realmObject: QuoteRealmModel) =
        CommonDataModel(realmObject.id, realmObject.content, realmObject.author, true)
}