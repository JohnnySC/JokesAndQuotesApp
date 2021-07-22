package com.github.johnnysc.jokeapp.data.cache

import android.content.Context
import com.github.johnnysc.jokeapp.core.data.cache.RealmProvider
import io.realm.Realm

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseRealmProvider(context: Context) : RealmProvider {
    init {
        Realm.init(context)
    }
    override fun provide(): Realm = Realm.getDefaultInstance()
}