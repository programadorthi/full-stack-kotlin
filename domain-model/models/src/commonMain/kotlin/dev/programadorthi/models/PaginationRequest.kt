package dev.programadorthi.models

public data class PaginationRequest(
    val pageSize: Int = 10,
    val pageIndex: Int = 0,
)
