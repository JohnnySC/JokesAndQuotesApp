package com.github.johnnysc.jokeapp.core

import androidx.annotation.StringRes

/**
 * @author Asatryan on 21.06.2021
 **/
interface ResourceManager {

    fun getString(@StringRes stringResId: Int): String
}