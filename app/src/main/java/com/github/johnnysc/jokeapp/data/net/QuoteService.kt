package com.github.johnnysc.jokeapp.data.net

import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Asatryan on 21.06.2021
 **/
interface QuoteService {

    @GET("https://api.quotable.io/random")
    fun getQuote(): Call<QuoteServerModel>
}