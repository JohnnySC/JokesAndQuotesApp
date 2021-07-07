package com.github.johnnysc.jokeapp.presentation

import androidx.lifecycle.*
import com.github.johnnysc.jokeapp.core.domain.CommonInteractor
import com.github.johnnysc.jokeapp.core.presentation.CommonCommunication
import com.github.johnnysc.jokeapp.core.presentation.CommonViewModel
import com.github.johnnysc.jokeapp.domain.CommonItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Asatryan on 13.06.2021
 **/
class BaseViewModel<T>(
    private val interactor: CommonInteractor<T>,
    private val communication: CommonCommunication<T>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel(), CommonViewModel<T> {

    override fun getItem() {
        viewModelScope.launch(dispatcher) {
            communication.showState(State.Progress)
            interactor.getItem().to().show(communication)
        }
    }

    override fun getItemList() {
        viewModelScope.launch(dispatcher) {
            showList()
        }
    }

    override fun changeItemStatus() {
        viewModelScope.launch(dispatcher) {
            if (communication.isState(State.INITIAL)) {
                interactor.changeFavorites().to().show(communication)
                showList()
            }
        }
    }

    override fun changeItemStatus(id: T) {
        viewModelScope.launch(dispatcher) {
            interactor.removeItem(id)
            showList()
        }
    }

    private suspend fun showList() = communication.showDataList(interactor.getItemList().toUiList())

    override fun chooseFavorites(favorites: Boolean) = interactor.getFavorites(favorites)
    override fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)

    override fun observeList(
        owner: LifecycleOwner,
        observer: Observer<List<CommonUiModel<T>>>
    ) = communication.observeList(owner, observer)
}

fun <T> List<CommonItem<T>>.toUiList() = map { it.to() }