package com.github.johnnysc.jokeapp.data.net

import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Asatryan on 13.06.2021
 **/
interface BaseJokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>
}