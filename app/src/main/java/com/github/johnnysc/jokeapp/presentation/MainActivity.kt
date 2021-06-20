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
        favoriteDataView.listenChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        favoriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }
        favoriteDataView.handleActionButton {
            viewModel.getJoke()
        }

        viewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })
    }
}