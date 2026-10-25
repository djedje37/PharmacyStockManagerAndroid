package com.djedje37.pharmacystockmanager.data.local.seed

import com.djedje37.pharmacystockmanager.data.local.entity.BatchEntity
import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity


// JSon -> Room

fun SeedProductDto.toEntity() = ProductEntity(
    cip = cip,
    name = name,
    category = category,
    publicPrice = publicPrice,
    reimbursementBase = reimbursementBase,
    vatRate = vatRate,
    alertThreshold = alertThreshold,
    geoCode = geoCode
)

fun BatchPlan.toEntity(productId: Long) = BatchEntity(
    productId = productId,
    quantity = quantity,
    expirationDate = expirationDate.toString(),
    receivedDate = receiveDate.toString(),
    purchasePrice = purchasePrice

)