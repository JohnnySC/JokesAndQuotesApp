package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.data.cache.RealmToCommonDataMapper

/**
 * @author Asatryan on 21.06.2021
 **/
class JokeRealmToCommonMapper : RealmToCommonDataMapper<JokeRealmModel, Int> {
    override fun map(realmObject: JokeRealmModel) =
        CommonDataModel(realmObject.id, realmObject.text, realmObject.punchLine, true)
}