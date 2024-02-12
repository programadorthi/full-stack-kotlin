package dev.programadorthi.full.stack.server.app.infrastructure.serializable

import kotlinx.serialization.json.Json

internal object JsonParser {
    val format = Json { prettyPrint = true }
}