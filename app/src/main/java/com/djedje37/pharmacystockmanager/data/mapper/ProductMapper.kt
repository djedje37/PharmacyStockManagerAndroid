package com.djedje37.pharmacystockmanager.data.mapper

import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity
import com.djedje37.pharmacystockmanager.domain.model.Product
import java.time.LocalDate


// entity to Domain model
fun ProductEntity.toDomain() = Product(
    id = id,
    cip = cip,
    name = name,
    category = category,
    publicPrice = publicPrice,
    reimbursementBase = reimbursementBase,
    vatRate = vatRate,
    alertThreshold = alertThreshold,
    geoCode = geoCode)

fun Product.toEntity() = ProductEntity(
    id = id,
    cip = cip,
    name = name,
    category = category,
    publicPrice = publicPrice,
    reimbursementBase = reimbursementBase,
    vatRate = vatRate,
    alertThreshold = alertThreshold,
    geoCode = geoCode,
)