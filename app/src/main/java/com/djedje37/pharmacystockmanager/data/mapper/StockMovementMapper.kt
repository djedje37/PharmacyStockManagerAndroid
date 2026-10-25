package com.djedje37.pharmacystockmanager.data.mapper

import com.djedje37.pharmacystockmanager.data.local.entity.StockMovementEntity
import com.djedje37.pharmacystockmanager.domain.model.MovementType
import com.djedje37.pharmacystockmanager.domain.model.StockMovement
import java.time.LocalDate

fun StockMovementEntity.toDomain() = StockMovement(
    id = id,
    productId = productId,
    type = MovementType.valueOf(type),
    quantity = quantity,
    date = LocalDate.parse(date)
)


fun StockMovement.toEntity() = StockMovementEntity(
    id = id,
    productId = productId,
    type = type.name,
    quantity = quantity,
    date = date.toString()
)