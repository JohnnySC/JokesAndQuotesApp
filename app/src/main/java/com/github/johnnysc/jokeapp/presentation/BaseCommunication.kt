package com.github.johnnysc.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import com.github.johnnysc.jokeapp.core.presentation.CommonCommunication

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseCommunication<T> : CommonCommunication<T> {
    private val liveData = MutableLiveData<State>()
    override fun isState(type: Int) = liveData.value?.isType(type) ?: false
    override fun showState(state: State) {
        liveData.value = state
    }

    private val listLiveData = MutableLiveData<ArrayList<CommonUiModel<T>>>()
    private lateinit var diffResult: DiffUtil.DiffResult

    override fun showDataList(list: List<CommonUiModel<T>>) {
        val callback = CommonDiffUtilCallback(getList(), list)
        diffResult = DiffUtil.calculateDiff(callback)
        listLiveData.value = ArrayList(list)
    }

    override fun observeList(owner: LifecycleOwner, observer: Observer<List<CommonUiModel<T>>>) {
        listLiveData.observe(owner, observer)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        liveData.observe(owner, observer)

    override fun getList(): List<CommonUiModel<T>> {
        return listLiveData.value ?: emptyList()
    }

    override fun getDiffResult() = diffResult
}