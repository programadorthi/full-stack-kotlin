package dev.programadorthi.full.stack.server.app.routing

import dev.programadorthi.full.stack.server.app.plugins.hashedUserTable
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*

internal fun Route.authenticationRouting() {
    post("/login") {
        val formParameters = call.receiveParameters()
        val username = formParameters["username"].toString()
        val password = formParameters["password"].toString()
        val credential = UserPasswordCredential(name = username, password = password)
        val result = hashedUserTable.authenticate(credential)
        if (result == null) {
            call.respond(status = HttpStatusCode.Unauthorized, message = "Invalid username or password")
        } else {
            val hash = "${username}:${password}".encodeBase64()
            call.respond(status = HttpStatusCode.OK, message = """{"basic": "$hash"}""")
        }
    }
}