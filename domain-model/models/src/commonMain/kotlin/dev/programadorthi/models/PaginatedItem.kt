package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class PaginatedItem<T>(
    val index: Int,
    val size: Int,
    val count: Int,
    val data: List<T>,
)
