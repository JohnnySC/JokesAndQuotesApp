package com.github.johnnysc.jokeapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 19.06.2021
 **/
open class JokeRealmModel : RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchLine: String = ""
}