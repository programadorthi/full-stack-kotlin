package dev.programadorthi.full.stack.server.app.helper

import io.ktor.server.application.*
import io.ktor.server.engine.*

internal interface ImageUrlGenerator {
    fun generate(id: Int): String
}

internal class ImageUrlGeneratorImpl(environment: ApplicationEnvironment) : ImageUrlGenerator {

    private val connector = (environment as ApplicationEngineEnvironment).connectors.first()

    override fun generate(id: Int): String =
        "http://${connector.host}:${connector.port}/item/image/$id"

}