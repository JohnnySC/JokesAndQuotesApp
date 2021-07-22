package com.github.johnnysc.jokeapp

import androidx.lifecycle.ViewModel
import com.github.johnnysc.jokeapp.presentation.BaseCommunication
import com.github.johnnysc.jokeapp.presentation.BaseViewModel

/**
 * @author Asatryan on 22.07.2021
 **/
interface Module<T : ViewModel> {
    fun getViewModel(): T

    abstract class Base<E, T : BaseViewModel<E>> : Module<T> {
        protected abstract fun getCommunications(): BaseCommunication<E>
    }
}