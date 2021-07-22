package com.github.johnnysc.jokeapp

import com.github.johnnysc.jokeapp.core.data.cache.PersistentDataSource
import com.github.johnnysc.jokeapp.presentation.MainViewModel
import com.github.johnnysc.jokeapp.presentation.NavigationCommunication
import com.github.johnnysc.jokeapp.presentation.ScreenPosition

/**
 * @author Asatryan on 22.07.2021
 **/
class MainModule(private val persistentDataSource: PersistentDataSource) : Module<MainViewModel> {
    override fun getViewModel() = MainViewModel(
        ScreenPosition.Base(persistentDataSource),
        NavigationCommunication.Base()
    )
}