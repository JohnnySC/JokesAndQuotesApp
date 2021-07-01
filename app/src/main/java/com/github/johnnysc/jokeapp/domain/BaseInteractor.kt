package com.github.johnnysc.jokeapp.domain

import com.github.johnnysc.jokeapp.core.data.CommonDataModelMapper
import com.github.johnnysc.jokeapp.core.data.CommonRepository
import com.github.johnnysc.jokeapp.core.domain.CommonInteractor
import com.github.johnnysc.jokeapp.core.domain.FailureHandler

/**
 * @author Asatryan on 19.06.2021
 **/
class BaseInteractor<E>(
    private val repository: CommonRepository<E>,
    private val failureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success<E>, E>
) : CommonInteractor<E> {
    override suspend fun getItem(): CommonItem<E> {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override suspend fun getItemList(): List<CommonItem<E>> {
        return try {
            repository.getCommonItemList().map {
                it.map(mapper)
            }
        } catch (e: Exception) {
            listOf(CommonItem.Failed(failureHandler.handle(e)))
        }
    }

    override suspend fun changeFavorites(): CommonItem<E> {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(failureHandler.handle(e))
        }
    }

    override fun getFavorites(favorites: Boolean) =
        repository.chooseDataSource(favorites)

    override suspend fun removeItem(id: E) {
        repository.removeItem(id)
    }
}