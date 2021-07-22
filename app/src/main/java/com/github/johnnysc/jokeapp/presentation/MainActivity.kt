package com.github.johnnysc.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.johnnysc.jokeapp.JokesAndQuotesApp
import com.github.johnnysc.jokeapp.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val screens = listOf(
        JokesFragment::class.java,
        QuotesFragment::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel =
            ViewModelProvider(this, (application as JokesAndQuotesApp).viewModelsFactory).get(
                MainViewModel::class.java
            )

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabChosen: (Int) -> Unit = { position ->
            viewModel.save(position)
        }
        tabLayout.addOnTabSelectedListener(TabListener(tabChosen))
        viewModel.observe(this) { position ->
            tabLayout.getTabAt(position)?.select()
            show(screens[position].newInstance())
        }
        viewModel.init()
    }

    private fun show(fragment: BaseFragment<*, *>) = with(supportFragmentManager) {
        if (fragments.isEmpty() || fragments.last().tag != fragment.tag())
            beginTransaction()
                .replace(R.id.container, fragment, fragment.tag())
                .commit()
    }
}

private class TabListener(private val tabChosen: (Int) -> Unit) : TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position ?: 0)
    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
}