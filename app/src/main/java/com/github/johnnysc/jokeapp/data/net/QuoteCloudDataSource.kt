package com.github.johnnysc.jokeapp.data.net

/**
 * @author Asatryan on 21.06.2021
 **/
class QuoteCloudDataSource(private val service: QuoteService) :
    BaseCloudDataSource<QuoteServerModel>() {
    override fun getServerModel() = service.getQuote()
}