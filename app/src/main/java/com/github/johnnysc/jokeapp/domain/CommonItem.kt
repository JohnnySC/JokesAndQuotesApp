package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.core.Mapper
import com.github.johnnysc.jokeapp.presentation.CommonUiModel
import com.github.johnnysc.jokeapp.core.presentation.Failure
import com.github.johnnysc.jokeapp.presentation.*

/**
 * @author Asatryan on 15.06.2021
 **/
sealed class CommonItem : Mapper<CommonUiModel> {
    class Success(
        private val firstText: String,
        private val secondText: String,
        private val favorite: Boolean
    ) : CommonItem() {
        override fun to(): CommonUiModel {
            return if (favorite) {
                FavoriteCommonUiModel(firstText, secondText)
            } else {
                BaseCommonUiModel(firstText, secondText)
            }
        }
    }

    class Failed(private val failure: Failure) : CommonItem() {
        override fun to(): CommonUiModel {
            return FailedCommonUiModel(failure.getMessage())
        }
    }
}