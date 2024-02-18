package dev.programadorthi.models.user

import kotlinx.serialization.Serializable

@Serializable
public data class Login(
    val username: String,
    val password: String,
)
