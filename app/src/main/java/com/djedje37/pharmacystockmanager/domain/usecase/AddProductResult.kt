package com.djedje37.pharmacystockmanager.domain.usecase

enum class AddProductError { InvalidNumber, BlankCip, BlankName, NegativeValue, DuplicateCip }

sealed interface AddProductResult {
    data object Success : AddProductResult
    data class Failure(val error: AddProductError) : AddProductResult
}