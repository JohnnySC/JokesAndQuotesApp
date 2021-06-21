package com.github.johnnysc.jokeapp.core.domain

import java.io.IOException

/**
 * @author Asatryan on 19.06.2021
 **/
class NoConnectionException : IOException()

class ServiceUnavailableException : IOException()

class NoCachedDataException : IOException()