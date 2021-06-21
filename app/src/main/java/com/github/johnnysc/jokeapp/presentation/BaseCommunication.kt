package com.github.johnnysc.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.github.johnnysc.jokeapp.core.presentation.Communication

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCommunication : Communication {
    private val liveData = MutableLiveData<State>()
    override fun isState(type: Int) = liveData.value?.isType(type) ?: false
    override fun showState(state: State) {
        liveData.value = state
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        liveData.observe(owner, observer)
}