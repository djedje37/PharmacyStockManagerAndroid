package com.djedje37.pharmacystockmanager.data.local.relation

import androidx.room3.Embedded
import androidx.room3.Relation
import com.djedje37.pharmacystockmanager.data.local.entity.BatchEntity
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity

data class ProductWithBatches(
    @Embedded val product: ProductEntity,
    @Relation(parentColumns = ["id"], entityColumns = ["productId"])
    val batches: List<BatchEntity>,
)