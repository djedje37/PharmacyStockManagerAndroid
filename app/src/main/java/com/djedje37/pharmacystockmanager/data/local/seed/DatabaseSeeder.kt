package com.djedje37.pharmacystockmanager.data.local.seed

import android.content.Context
import android.util.Log
import com.djedje37.pharmacystockmanager.data.local.dao.BatchDao
import com.djedje37.pharmacystockmanager.data.local.dao.ProductDao
import com.djedje37.pharmacystockmanager.data.local.dao.StockMovementDao
import com.djedje37.pharmacystockmanager.data.local.entity.StockMovementEntity
import com.djedje37.pharmacystockmanager.domain.model.MovementType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.time.LocalDate

class DatabaseSeeder(
    private val context : Context,
    private val productDao: ProductDao,
    private val batchDao: BatchDao,
    private val movementDao: StockMovementDao,
    private val generator: SeedBatchGenerator = SeedBatchGenerator()
) {

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
    }
    suspend fun seedIfEmpty() = withContext(Dispatchers.IO) {
        Log.d("productDao", productDao.count().toString())
        if (productDao.count() > 0)  return@withContext
        val json = context.assets
            .open("products_seed.json")
            .bufferedReader()
            .use { it.readText()}

        val productsDto = jsonConfig
            .decodeFromString<List<SeedProductDto>>(json)

        val today = LocalDate.now()

        productsDto.forEach { dto ->
            val productId = productDao.insert(dto.toEntity())
            if (productId == -1L) return@forEach // product already exist in bdd

            // Step 1 : generate plans
            val plans = generator.generatePlans(
                stock = dto.currentStock,
                purchasePrice = dto.purchasePrice,
                knownExpirationDate = dto.knownExpirationDate?.let(LocalDate::parse),
                today = today
            )
            // Step 2 : insert all batches
            batchDao.insertAll(plans.map { it.toEntity(productId) })

            // Step 3 : insert last sale
            dto.lastSaleDate?.let { date ->
                movementDao.insert(
                    StockMovementEntity(
                        productId = productId,
                        type = MovementType.EXIT,
                        quantity = 1,
                        date = LocalDate.parse(date).toString()
                    )
                )

            }



        }

    }
    suspend fun intializeFromJson(){

        val json = context.assets
            .open("products_seed.json")
            .bufferedReader()
            .use { it.readText()}

    }
}