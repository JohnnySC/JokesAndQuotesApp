package com.github.johnnysc.jokeapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.github.johnnysc.jokeapp.presentation.CommonUiModel
import com.github.johnnysc.jokeapp.presentation.State

/**
 * @author Asatryan on 21.06.2021
 **/
interface CommonViewModel {
    fun getItem()
    fun getItemList()
    fun changeItemStatus()
    fun chooseFavorites(favorites: Boolean)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>)
}