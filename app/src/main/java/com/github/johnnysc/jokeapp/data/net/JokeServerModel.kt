package com.github.johnnysc.jokeapp.data.net

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.data.JokeDataModel
import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 13.06.2021
 **/
data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("punchline")
    private val punchline: String
) : Mapper<JokeDataModel> {
    override fun to() = JokeDataModel(id, text, punchline)
}