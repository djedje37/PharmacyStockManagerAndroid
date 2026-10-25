package com.djedje37.pharmacystockmanager.data.mapper

import com.djedje37.pharmacystockmanager.data.local.entity.BatchEntity
import com.djedje37.pharmacystockmanager.domain.model.Batch
import java.time.LocalDate


fun BatchEntity.toDomain() = Batch(
    id = id,
    productId = productId,
    expirationDate = LocalDate.parse(expirationDate),
    receivedDate = LocalDate.parse(receivedDate),
    purchasePrice = purchasePrice,
    quantity = quantity

)

fun Batch.toEntity() = BatchEntity(
    id = id,
    productId = productId,
    expirationDate = expirationDate.toString(),
    receivedDate = receivedDate.toString(),
    purchasePrice = purchasePrice,
    quantity = quantity
)