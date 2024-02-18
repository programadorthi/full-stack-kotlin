package dev.programadorthi.full.stack.server.app.routing

import dev.programadorthi.full.stack.interactors.user.LoginInteractor
import dev.programadorthi.full.stack.server.app.repository.LoginRepositoryImpl
import dev.programadorthi.models.api.ApiError
import dev.programadorthi.models.user.Login
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

internal fun Route.authenticationRouting() {
    post("/login") {
        val login = call.receive<Login>()
        val interactor = LoginInteractor(LoginRepositoryImpl())
        interactor.username.update { login.username }
        interactor.password.update { login.password }
        interactor.login()
            .onSuccess { token ->
                call.respond(
                    status = HttpStatusCode.OK,
                    message = token
                )
            }
            .onFailure { error ->
                call.respond(
                    status = HttpStatusCode.Unauthorized,
                    message = ApiError(
                        message = error.message ?: "Invalid username or password"
                    )
                )
            }
    }
}