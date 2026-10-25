package com.djedje37.pharmacystockmanager.domain.model

import kotlinx.serialization.Serializable


enum class VATRate(val rawValue: Int, val percentage: Double) {
    EXEMPT(1, 0.0),
    STANDARD(2, 18.0);

    companion object {
        fun fromRaw(raw: Int): VATRate = entries.firstOrNull { it.rawValue == raw } ?: EXEMPT
    }
}