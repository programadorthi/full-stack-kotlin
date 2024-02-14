package dev.programadorthi.full.stack.server.app.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
