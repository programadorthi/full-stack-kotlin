package dev.programadorthi.full.stack.server.app

import dev.programadorthi.full.stack.server.app.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

public fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

internal fun Application.module() {
    configureSerialization()
    configureRouting()
}
