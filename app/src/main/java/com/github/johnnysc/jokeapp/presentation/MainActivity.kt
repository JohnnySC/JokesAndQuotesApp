package com.github.johnnysc.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.github.johnnysc.jokeapp.JokeApp
import com.github.johnnysc.jokeapp.R

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

        val quoteViewModel = (application as JokeApp).quoteViewModel
        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteFavoriteView)
        quoteFavoriteDataView.linkWith(quoteViewModel)
        quoteViewModel.observe(this) { state ->
            quoteFavoriteDataView.show(state)
        }
    }
}