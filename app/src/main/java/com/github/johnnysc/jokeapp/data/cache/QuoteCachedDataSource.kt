package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.data.mapper.QuoteRealmMapper

/**
 * @author Asatryan on 21.06.2021
 **/
class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonDataMapper: QuoteRealmToCommonMapper
) : BaseCachedDataSource<QuoteRealmModel>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = QuoteRealmModel::class.java
}