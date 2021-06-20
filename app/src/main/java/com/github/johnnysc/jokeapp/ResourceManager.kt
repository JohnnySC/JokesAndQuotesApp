package com.github.johnnysc.jokeapp

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Asatryan on 13.06.2021
 **/
interface ResourceManager {

    fun getString(@StringRes stringResId: Int) : String
}

class BaseResourceManager(private val context: Context) : ResourceManager {

    override fun getString(stringResId: Int) = context.getString(stringResId)
}