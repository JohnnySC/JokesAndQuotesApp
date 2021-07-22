package com.github.johnnysc.jokeapp.sl

import com.github.johnnysc.jokeapp.presentation.MainViewModel
import com.github.johnnysc.jokeapp.presentation.NavigationCommunication
import com.github.johnnysc.jokeapp.presentation.ScreenPosition

/**
 * @author Asatryan on 22.07.2021
 **/
class MainModule(private val commonInstancesProvider: CommonInstancesProvider) : Module<MainViewModel> {

    override fun getViewModel() = MainViewModel(
        ScreenPosition.Base(commonInstancesProvider.providePersistentDataSource()),
        NavigationCommunication.Base()
    )
}