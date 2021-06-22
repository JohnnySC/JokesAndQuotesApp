package com.github.johnnysc.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.jokeapp.JokeApp
import com.github.johnnysc.jokeapp.R
import com.github.johnnysc.jokeapp.data.CommonDataModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = (application as JokeApp).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter()
        recyclerView.adapter = adapter
        viewModel.observeList(this, { list ->
            adapter.show(list)
        })
        viewModel.getItemList()
    }
}