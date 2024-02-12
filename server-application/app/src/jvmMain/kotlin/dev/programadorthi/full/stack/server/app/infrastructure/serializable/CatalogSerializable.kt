package dev.programadorthi.full.stack.server.app.infrastructure.serializable

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class CatalogSerializable(
    @SerialName("Id") val id: Int,
    @SerialName("Name") val name: String,
    @SerialName("Description") val description: String,
    @SerialName("Price") val price: Float,
    @SerialName("Type") val type: String,
    @SerialName("Brand") val brand: String,
    @SerialName("Embedding") val embedding: List<Float>,
)