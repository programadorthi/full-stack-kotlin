package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class CatalogItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val pictureFileName: String,
    val pictureUri: String,
    val availableStock: Int,
    val maxStockThreshold: Int,
    val restockThreshold: Int,
    val brand: CatalogBrand,
    val type: CatalogType
)