package com.github.johnnysc.jokeapp.core.presentation

/**
 * @author Asatryan on 21.06.2021
 **/
interface Show<T> {
    fun show(arg: T)
}

interface ShowText : Show<String>
interface ShowImage : Show<Int>
interface ShowView : Show<Boolean>

interface EnableView {
    fun enable(enable: Boolean)
}