package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.data.mapper.JokeRealmMapper

/**
 * @author Asatryan on 15.06.2021
 **/
class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper: JokeRealmMapper,
    commonDataMapper: JokeRealmToCommonMapper
) : BaseCachedDataSource<JokeRealmModel>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = JokeRealmModel::class.java
}