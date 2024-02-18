package dev.programadorthi.models.api

import kotlinx.serialization.Serializable

@Serializable
public data class ApiError(
    val id: String = "",
    val message: String = ""
)
