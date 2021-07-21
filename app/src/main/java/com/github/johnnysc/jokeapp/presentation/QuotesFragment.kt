package com.github.johnnysc.jokeapp.presentation

import com.github.johnnysc.jokeapp.JokeApp
import com.github.johnnysc.jokeapp.R

/**
 * @author Asatryan on 21.07.2021
 **/
class QuotesFragment : BaseFragment<String>() {
    override fun getViewModel(app: JokeApp) = app.quoteViewModel
    override fun getCommunication(app: JokeApp) = app.quoteCommunication
    override fun checkBoxText() = R.string.show_favorite_quote
    override fun actionButtonText() = R.string.get_quote
}