package com.github.johnnysc.jokeapp.presentation

import com.github.johnnysc.jokeapp.JokeApp
import com.github.johnnysc.jokeapp.R

/**
 * @author Asatryan on 21.07.2021
 **/
class JokesFragment : BaseFragment<Int>() {
    override fun getViewModel(app: JokeApp) = app.jokeViewModel
    override fun getCommunication(app: JokeApp) = app.jokeCommunication
    override fun checkBoxText() = R.string.show_favorite_joke
    override fun actionButtonText() = R.string.get_joke
}