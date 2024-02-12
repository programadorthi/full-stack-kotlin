package dev.programadorthi.full.stack.server.app.infrastructure

import java.net.URL

internal object Resources {

    fun catalogJson(): URL? =
        javaClass.classLoader.getResource("json/catalog.json")

    fun image(name: String): URL? =
        javaClass.classLoader.getResource(name)

}