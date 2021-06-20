package com.github.johnnysc.jokeapp.data.cache

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.data.JokeDataModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * @author Asatryan on 19.06.2021
 **/
open class JokeRealmModel : RealmObject(), Mapper<JokeDataModel> {
    @PrimaryKey
    var id: Int = -1
    var text: String = ""
    var punchLine: String = ""

    override fun to() = JokeDataModel(id, text, punchLine, true)
}