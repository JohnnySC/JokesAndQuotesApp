package com.github.johnnysc.jokeapp.core.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.github.johnnysc.jokeapp.presentation.CommonUiModel
import com.github.johnnysc.jokeapp.presentation.State

/**
 * @author Asatryan on 19.06.2021
 **/
interface Communication {
    fun showState(state: State)
    fun observe(owner: LifecycleOwner, observer: Observer<State>)
    fun isState(type: Int): Boolean
}

interface ListCommunication<T> : ListChanges<T> {
    fun showDataList(list: List<CommonUiModel<T>>)
    fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>)
}

interface ListChanges<T> {
    fun getList(): List<CommonUiModel<T>>
    fun getDiffResult(): DiffUtil.DiffResult
}

interface CommonCommunication<T> : Communication, ListCommunication<T>