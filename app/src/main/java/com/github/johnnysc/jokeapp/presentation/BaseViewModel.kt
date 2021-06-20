package com.github.johnnysc.jokeapp.presentation

import androidx.lifecycle.*
import com.github.johnnysc.jokeapp.domain.JokeInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author Asatryan on 13.06.2021
 **/
class BaseViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun getJoke() = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        interactor.getJoke().to().show(communication)
    }
    fun changeJokeStatus() = viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL))
            interactor.changeFavorites().to().show(communication)
    }
    fun chooseFavorites(favorites: Boolean) = interactor.getFavoriteJokes(favorites)
    fun observe(owner: LifecycleOwner, observer: Observer<State>) =
        communication.observe(owner, observer)
}