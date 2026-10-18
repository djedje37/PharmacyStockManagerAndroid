package com.djedje37.pharmacystockmanager

import android.content.Context
import androidx.room3.Room
import com.djedje37.pharmacystockmanager.data.local.PharmacyDatabase
import com.djedje37.pharmacystockmanager.data.local.seed.DatabaseSeeder
import com.djedje37.pharmacystockmanager.data.repository.ProductRepositoryImpl
import com.djedje37.pharmacystockmanager.domain.repository.ProductRepository
import com.djedje37.pharmacystockmanager.domain.usecase.ObserveProductsUseCase

class AppContainer(context: Context) {

    private val db = Room.databaseBuilder(context, PharmacyDatabase::class.java, "pharmacy.db").build()
    private val dao = db.productDao()
    private val productRepository : ProductRepository = ProductRepositoryImpl(dao)
    val observeProductsUseCase = ObserveProductsUseCase(productRepository)
    val databaseSeeder = DatabaseSeeder(context, dao)
}