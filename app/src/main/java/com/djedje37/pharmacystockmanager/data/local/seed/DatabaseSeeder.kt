package com.djedje37.pharmacystockmanager.data.local.seed

import android.content.Context
import android.util.Log
import com.djedje37.pharmacystockmanager.data.local.dao.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
class DatabaseSeeder(private val context : Context, private val productDao: ProductDao) {

    suspend fun seedIfEmpty() = withContext(Dispatchers.IO) {
        Log.d("productDao", productDao.count().toString())
        if (productDao.count() > 0)  return@withContext
        val json = context.assets
            .open("products_seed.json")
            .bufferedReader()
            .use { it.readText()}

        val products = Json { ignoreUnknownKeys = true }
            .decodeFromString<List<SeedProductDto>>(json)

        productDao.insertAll(products.map {it.toEntity()})
    }
    suspend fun intializeFromJson(){

        val json = context.assets
            .open("products_seed.json")
            .bufferedReader()
            .use { it.readText()}

    }
}