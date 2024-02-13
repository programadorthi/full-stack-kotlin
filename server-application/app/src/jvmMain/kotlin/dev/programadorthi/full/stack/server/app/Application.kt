package dev.programadorthi.full.stack.server.app

import dev.programadorthi.full.stack.server.app.infrastructure.DatabaseSingleton
import dev.programadorthi.full.stack.server.app.plugins.configureAuthorization
import dev.programadorthi.full.stack.server.app.plugins.configureRouting
import dev.programadorthi.full.stack.server.app.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

public fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

internal fun Application.module() {
    DatabaseSingleton.init()
    configureAuthorization()
    configureSerialization()
    configureRouting()
}
