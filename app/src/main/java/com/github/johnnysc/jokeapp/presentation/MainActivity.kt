package com.github.johnnysc.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.jokeapp.JokeApp
import com.github.johnnysc.jokeapp.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CommonDataRecyclerAdapter<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as JokeApp).viewModel
        val jokeCommunication = (application as JokeApp).jokeCommunication
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<Int> {
            override fun change(id: Int) {
                Snackbar.make(
                    favoriteDataView,
                    R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes) {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, jokeCommunication)
        recyclerView.adapter = adapter

        viewModel.observeList(this, {
            adapter.update()
        })
        viewModel.getItemList()
    }
}