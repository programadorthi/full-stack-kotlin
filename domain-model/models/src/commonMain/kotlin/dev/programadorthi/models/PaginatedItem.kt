package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class PaginatedItem<T>(
    val index: Long,
    val size: Int,
    val count: Long,
    val data: List<T>,
)
