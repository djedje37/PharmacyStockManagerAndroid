package com.djedje37.pharmacystockmanager.testing

import com.djedje37.pharmacystockmanager.domain.model.Batch
import com.djedje37.pharmacystockmanager.domain.model.Product
import com.djedje37.pharmacystockmanager.domain.model.ProductCategory
import com.djedje37.pharmacystockmanager.domain.model.VATRate
import java.time.LocalDate

fun batch(
    id : Long = 1,
    productId: Long = 1,
    quantity: Int = 10,
    expirationDate: LocalDate = LocalDate.of(2030, 1,1),
    receivedDate: LocalDate = LocalDate.of(2026, 1,1),
    purchasePrice: Double = 5.0
) = Batch(
    id = id,
    productId = productId,
    quantity = quantity,
    expirationDate = expirationDate,
    receivedDate = receivedDate,
    purchasePrice = purchasePrice

)

fun product(
    id: Long = 1,
    cip: String = "3400000001",
    name: String = "Produit to Test",
    category: String = ProductCategory.entries.first().toString(),
    publicPrice: Double = 20.0,
    reimbursementBase: Double = 10.0,
    valRate: VATRate = VATRate.entries.first(),
    alertThreshold: Int = 5,
    geoCode : String = "AES",
    batches: List<Batch> = emptyList()
) = Product(
    id = id,
    cip = cip,
    name = name,
    category = category,
    publicPrice = publicPrice,
    reimbursementBase = reimbursementBase,
    vatRate = valRate.rawValue,
    alertThreshold = alertThreshold,
    geoCode = geoCode,
    batches = batches

)