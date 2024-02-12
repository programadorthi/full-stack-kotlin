package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class CatalogBrand(
    val id: Int,
    val name: String,
)
