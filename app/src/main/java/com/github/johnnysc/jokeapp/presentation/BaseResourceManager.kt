package com.github.johnnysc.jokeapp.presentation

import android.content.Context
import com.github.johnnysc.jokeapp.core.ResourceManager

/**
 * @author Asatryan on 13.06.2021
 **/
class BaseResourceManager(private val context: Context) : ResourceManager {

    override fun getString(stringResId: Int) = context.getString(stringResId)
}