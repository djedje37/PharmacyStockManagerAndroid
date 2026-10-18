package com.djedje37.pharmacystockmanager.data.local.seed

import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity


// JSon -> Room

fun SeedProductDto.toEntity() = ProductEntity(cip = cip, name = name, category = category,
    publicPrice = publicPrice, purchasePrice = purchasePrice,
    reimbursementBase = reimbursementBase, vatRate = vatRate,
    currentStock = currentStock, alertThreshold = alertThreshold,
    lastSaleDate = lastSaleDate, knownExpirationDate = knownExpirationDate,
    geoCode = geoCode,)