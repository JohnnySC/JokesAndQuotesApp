package com.github.johnnysc.jokeapp.presentation

import androidx.annotation.DrawableRes
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.core.presentation.Communication
import com.github.johnnysc.jokeapp.core.presentation.ShowText

/**
 * @author Asatryan on 13.06.2021
 **/
class BaseCommonUiModel<E>(text: String, punchline: String) : CommonUiModel<E>(text, punchline) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteCommonUiModel<E>(private val id: E, text: String, punchline: String) :
    CommonUiModel<E>(text, punchline) {
    override fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<E>) =
        listener.change(id)

    override fun matches(id: E): Boolean  = this.id == id
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedCommonUiModel<E>(private val text: String) : CommonUiModel<E>(text, "") {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel<T>(private val first: String, private val second: String) {
    protected open fun text() = "$first\n$second"
    open fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<T>) = Unit
    open fun matches(id: T) : Boolean = false
    fun show(showText: ShowText) = showText.show(text())
    @DrawableRes
    protected abstract fun getIconResId(): Int
    open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )
}