package com.github.johnnysc.jokeapp.data.net

import com.github.johnnysc.jokeapp.data.CommonDataModel
import com.github.johnnysc.jokeapp.core.Mapper
import com.google.gson.annotations.SerializedName

/**
 * @author Asatryan on 21.06.2021
 **/
class QuoteServerModel(
    @SerializedName("_id")
    private val id: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String
) : Mapper<CommonDataModel<String>> {
    override fun to() = CommonDataModel(id, content, author)
}