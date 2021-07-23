package com.github.johnnysc.jokeapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.github.johnnysc.jokeapp.sl.*

/**
 * @author Asatryan on 13.06.2021
 **/
class JokesAndQuotesApp : Application() {

    private val viewModelsFactory by lazy {
        ViewModelsFactory(
            MainModule(coreModule),
            JokesModule(coreModule, useMocks),
            QuotesModule(coreModule)
        )
    }

    private lateinit var coreModule: CoreModule
    private val useMocks = BuildConfig.DEBUG

    override fun onCreate() {
        super.onCreate()
        coreModule = CoreModule(this, useMocks)
    }

    fun <T : ViewModel> get(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelsFactory).get(modelClass)
}