package dev.programadorthi.full.stack.server.app.plugins

import dev.programadorthi.full.stack.server.app.helper.ImageUrlGeneratorImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.BrandFacadeImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.CatalogFacadeImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.TypeFacadeImpl
import dev.programadorthi.full.stack.server.app.routing.authenticationRouting
import dev.programadorthi.full.stack.server.app.routing.catalogRouting
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.partialcontent.*
import io.ktor.server.routing.*

internal fun Application.configureRouting() {
    install(PartialContent)
    install(AutoHeadResponse)

    val imageUrlGenerator = ImageUrlGeneratorImpl(environment)

    routing {
        application.brandFacade = BrandFacadeImpl()
        application.typeFacade = TypeFacadeImpl()
        application.catalogFacade = CatalogFacadeImpl(imageUrlGenerator)

        authenticationRouting()

        authenticate(AUTH_BASIC) {
            catalogRouting()
        }
    }
}
