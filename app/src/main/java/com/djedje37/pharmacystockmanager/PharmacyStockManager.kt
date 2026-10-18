package com.djedje37.pharmacystockmanager

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.math.log

class PharmacyStockManager: Application() {

    lateinit var container: AppContainer
        private set
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
        Log.d("onCreate", "onCreate PharmaManager")
        appScope.launch { container.databaseSeeder.seedIfEmpty() }
    }

}