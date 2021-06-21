package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.core.data.cache.CacheDataSource
import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import com.github.johnnysc.jokeapp.core.data.cache.RealmToCommonDataMapper
import com.github.johnnysc.jokeapp.core.domain.NoCachedDataException
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Asatryan on 21.06.2021
 **/
abstract class BaseCachedDataSource<T : RealmObject>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T>,
    private val realmToCommonDataMapper: RealmToCommonDataMapper<T>
) : CacheDataSource {

    protected abstract val dbClass: Class<T>

    override suspend fun getData(): CommonDataModel {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return realmToCommonDataMapper.map(list.random())
        }
    }

    override suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm =
                    it.where(dbClass).equalTo("id", id).findFirst()
                return@withContext if (itemRealm == null) {
                    it.executeTransaction { transaction ->
                        val newData = model.map(mapper)
                        transaction.insert(newData)
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}