package com.github.johnnysc.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.jokeapp.core.presentation.CommonCommunication
import com.github.johnnysc.jokeapp.core.presentation.Communication

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCommunication : CommonCommunication {
    private val liveData = MutableLiveData<State>()
    override fun isState(type: Int) = liveData.value?.isType(type) ?: false
    override fun showState(state: State) {
        liveData.value = state
    }

    private val listLiveData = MutableLiveData<List<CommonUiModel>>()
    override fun showDataList(list: List<CommonUiModel>) {
        listLiveData.value = list
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel>>) {
        listLiveData.observe(owner, observer)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        liveData.observe(owner, observer)
}