package dev.programadorthi.full.stack.server.app.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.util.*

internal const val AUTH_BASIC = "auth-basic"
private const val USER_NAME = "fullstack"
private const val PASSWORD = "kotlin"

private val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }

internal val hashedUserTable = UserHashedTableAuth(
    table = mapOf(
        USER_NAME to digestFunction(PASSWORD)
    ),
    digester = digestFunction
)

internal fun Application.configureAuthorization() {
    install(Authentication) {
        basic(AUTH_BASIC) {
            realm = "Authentication is required to this content"
            validate { credential ->
                hashedUserTable.authenticate(credential)
            }
        }
    }
}