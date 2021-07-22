package com.github.johnnysc.jokeapp.presentation

import com.github.johnnysc.jokeapp.R

/**
 * @author Asatryan on 21.07.2021
 **/
class QuotesFragment : BaseFragment<QuotesViewModel, String>() {
    override fun getViewModelClass() = QuotesViewModel::class.java
    override fun checkBoxText() = R.string.show_favorite_quote
    override fun actionButtonText() = R.string.get_quote
}