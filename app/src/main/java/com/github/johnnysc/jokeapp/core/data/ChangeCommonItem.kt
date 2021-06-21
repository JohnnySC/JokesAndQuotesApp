package com.github.johnnysc.jokeapp.core.data

import com.github.johnnysc.jokeapp.data.CommonDataModel
import java.lang.IllegalStateException

/**
 * @author Asatryan on 19.06.2021
 **/
interface ChangeCommonItem {
    suspend fun change(changeStatus: ChangeStatus): CommonDataModel

    class Empty : ChangeCommonItem {
        override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change item called")
        }
    }
}