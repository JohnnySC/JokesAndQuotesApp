package com.github.johnnysc.jokeapp.core.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import io.realm.RealmObject

/**
 * @author Asatryan on 21.06.2021
 **/
interface RealmToCommonDataMapper<T : RealmObject> {

    fun map(realmObject: T): CommonDataModel
}