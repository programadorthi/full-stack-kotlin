package dev.programadorthi.models.serialization

import kotlinx.serialization.json.Json

public object JsonParser {
    public val instance: Json = Json { prettyPrint = true }
}