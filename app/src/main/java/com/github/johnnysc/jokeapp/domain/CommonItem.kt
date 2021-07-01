package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.presentation.CommonUiModel
import com.github.johnnysc.jokeapp.core.presentation.Failure
import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 15.06.2021
 **/
sealed class CommonItem<E> : Mapper<CommonUiModel<E>> {
    class Success<E>(
        private val id: E,
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem<E>() {
        override fun to() = if (favorite) {
            FavoriteCommonUiModel(id, firstText, secondText)
        } else {
            BaseCommonUiModel(firstText, secondText)
        }
    }

    class Failed<E>(private val failure: Failure) : CommonItem<E>() {
        override fun to(): CommonUiModel<E> = FailedCommonUiModel(failure.getMessage())
    }
}