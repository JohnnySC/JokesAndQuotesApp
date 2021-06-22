package com.github.johnnysc.jokeapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 21.06.2021
 **/
open class QuoteRealmModel : RealmObject() {
    @PrimaryKey
    var id: String = ""
    var content: String = ""
    var author: String = ""
}