package dev.programadorthi.models.user

import kotlinx.serialization.Serializable

@Serializable
public data class BasicToken(
    val basic: String
)
