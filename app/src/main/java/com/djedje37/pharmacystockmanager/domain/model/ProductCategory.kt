package com.djedje37.pharmacystockmanager.domain.model


enum class ProductCategory(val rawValue: String) {
    COMPRIME("Comprimé"),
    SIROP("Sirop / Solution buvable"),
    GELULE("Gélule"),
    INJECTABLE("Injectable"),
    COLLYRE("Collyre"),
    SACHET("Sachet"),
    DISPOSITIF_MEDICAL("Dispositif médical"),
    CREME_POMMADE("Crème / Pommade"),
    NUTRITION_INFANTILE("Nutrition infantile"),
    HYGIENE_PARAPHARMACIE("Hygiène / Parapharmacie"),
    AUTRE("Autre");

    companion object {
        fun fromRaw(raw: String): ProductCategory =
            entries.firstOrNull { it.rawValue == raw } ?: AUTRE
    }
}