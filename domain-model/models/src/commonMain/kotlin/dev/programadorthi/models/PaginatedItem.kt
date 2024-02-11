package dev.programadorthi.models

public data class PaginatedItem<T>(
    val index: Int,
    val size: Int,
    val count: Int,
    val data: List<T>,
)
