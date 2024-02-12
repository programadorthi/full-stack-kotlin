package dev.programadorthi.models

import kotlinx.serialization.Serializable

@Serializable
public data class PaginationRequest(
    val pageSize: Int = 10,
    val pageIndex: Long = 0,
)
