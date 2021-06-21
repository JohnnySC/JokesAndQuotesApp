package com.github.johnnysc.jokeapp.presentation

import androidx.annotation.DrawableRes
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.core.presentation.Communication

/**
 * @author Asatryan on 13.06.2021
 **/
class BaseCommonUiModel(text: String, punchline: String) : CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteCommonUiModel(text: String, punchline: String) : CommonUiModel(text, punchline) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedCommonUiModel(private val text: String) : CommonUiModel(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel(private val first: String, private val second: String) {
    protected open fun text() = "$first\n$second"

    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )
}