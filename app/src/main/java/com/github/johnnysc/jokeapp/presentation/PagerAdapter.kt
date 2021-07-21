package com.github.johnnysc.jokeapp.presentation

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author Asatryan on 21.07.2021
 **/
class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int) = if (position == 0)
        JokesFragment()
    else
        QuotesFragment()
}