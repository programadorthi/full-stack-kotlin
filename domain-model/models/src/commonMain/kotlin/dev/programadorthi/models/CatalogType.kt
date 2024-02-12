package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class CatalogType(
    val id: Int,
    val name: String,
)
