package com.github.johnnysc.jokeapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.github.johnnysc.jokeapp.R

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.jokeapp.JokesAndQuotesApp
import com.google.android.material.snackbar.Snackbar

/**
 * @author Asatryan on 21.07.2021
 **/
abstract class BaseFragment<V : BaseViewModel<T>, T> : Fragment() {

    private lateinit var viewModel: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            (requireActivity().application as JokesAndQuotesApp).viewModelsFactory
        ).get(getViewModelClass())
    }

    protected abstract fun getViewModelClass(): Class<V>

    @StringRes
    protected abstract fun checkBoxText(): Int
    @StringRes
    protected abstract fun actionButtonText(): Int

    fun tag(): String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<T> {
            override fun change(id: T) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, viewModel.communication)
        recyclerView.adapter = adapter

        viewModel.observeList(this, {
            adapter.update()
        })
        viewModel.getItemList()
    }
}