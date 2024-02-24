package dev.programadorthi.full.stack.compose.app.repository

import dev.programadorthi.full.stack.interactors.user.LoginRepository
import dev.programadorthi.models.serialization.JsonParser
import dev.programadorthi.models.user.BasicToken
import dev.programadorthi.models.user.Login
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json

internal class LoginRepositoryImpl : LoginRepository {
    private val client: HttpClient = HttpClient {
        defaultRequest {
            host = "localhost"
            port = 8080
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(JsonParser.instance)
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }
    }

    override suspend fun doLogin(login: Login): BasicToken =
        client.post {
            url("/login")
            setBody(login)
        }.body()

}