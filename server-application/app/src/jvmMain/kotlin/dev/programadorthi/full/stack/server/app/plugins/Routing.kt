package dev.programadorthi.full.stack.server.app.plugins

import dev.programadorthi.full.stack.server.app.infrastructure.DatabaseSingleton
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Catalog
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

internal fun Application.configureRouting() {
    routing {
        get("/") {
            val catalog = DatabaseSingleton.dbQuery {
                Catalog.findById(id = 1)
            }
            call.respondText("Hello World! -> ${catalog?.name}")
        }
    }
}
