package com.github.johnnysc.jokeapp.data.net

/**
 * @author Asatryan on 19.06.2021
 **/
class JokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getServerModel() = service.getJoke()
}