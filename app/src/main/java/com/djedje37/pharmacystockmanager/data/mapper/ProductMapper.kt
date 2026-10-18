package com.djedje37.pharmacystockmanager.data.mapper

import com.djedje37.pharmacystockmanager.data.local.entity.ProductEntity
import com.djedje37.pharmacystockmanager.domain.model.Product
import java.time.LocalDate


// entity to Domain model
fun ProductEntity.toDomain() = Product(    id = id, cip = cip, name = name, category = category,
    publicPrice = publicPrice, purchasePrice = purchasePrice,
    reimbursementBase = reimbursementBase, vatRate = vatRate,
    currentStock = currentStock, alertThreshold = alertThreshold,
    lastSaleDate = lastSaleDate?.let(LocalDate::parse),
    knownExpirationDate = knownExpirationDate?.let(LocalDate::parse),
    geoCode = geoCode,)