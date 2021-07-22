package com.github.johnnysc.jokeapp.presentation

import com.github.johnnysc.jokeapp.R

/**
 * @author Asatryan on 21.07.2021
 **/
class JokesFragment : BaseFragment<JokesViewModel, Int>() {
    override fun checkBoxText() = R.string.show_favorite_joke
    override fun actionButtonText() = R.string.get_joke
    override fun getViewModelClass() = JokesViewModel::class.java
}