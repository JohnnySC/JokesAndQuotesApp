package com.github.johnnysc.jokeapp.domain

import java.io.IOException

/**
 * @author Asatryan on 19.06.2021
 **/
class NoConnectionException : IOException()

class ServiceUnavailableException : IOException()

class NoCachedJokesException : IOException()