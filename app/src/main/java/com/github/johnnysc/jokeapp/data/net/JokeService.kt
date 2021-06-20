package com.github.johnnysc.jokeapp.data.net

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.data.JokeDataModel
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Asatryan on 13.06.2021
 **/
interface BaseJokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>
}

interface NewJokeService {

    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke(): Call<NewJokeServerModel>
}

class NewJokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String
) : Mapper<JokeDataModel> {
    override fun to() = JokeDataModel(id, text, punchline)
}