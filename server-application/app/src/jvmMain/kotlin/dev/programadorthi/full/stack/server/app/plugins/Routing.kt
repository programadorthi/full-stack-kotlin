package dev.programadorthi.full.stack.server.app.plugins

import dev.programadorthi.full.stack.server.app.helper.ImageUrlGeneratorImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.BrandFacadeImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.CatalogFacadeImpl
import dev.programadorthi.full.stack.server.app.infrastructure.facade.TypeFacadeImpl
import dev.programadorthi.models.PaginatedItem
import dev.programadorthi.models.PaginationRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

internal fun Application.configureRouting() {
    val imageUrlGenerator = ImageUrlGeneratorImpl(environment)

    routing {
        application.brandFacade = BrandFacadeImpl()
        application.typeFacade = TypeFacadeImpl()
        application.catalogFacade = CatalogFacadeImpl(imageUrlGenerator)

        route("/items") {
            get {
                getAllItems()
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getAllItems() {
    val paginationRequest = call.receive<PaginationRequest>()
    val catalogFacade = application.catalogFacade
    val totalItems = catalogFacade.count()
    val data = catalogFacade.catalogs(paginationRequest)
    val paginatedItem = PaginatedItem(
        index = paginationRequest.pageIndex,
        size = paginationRequest.pageSize,
        count = totalItems,
        data = data,
    )
    call.respond(paginatedItem)
}
