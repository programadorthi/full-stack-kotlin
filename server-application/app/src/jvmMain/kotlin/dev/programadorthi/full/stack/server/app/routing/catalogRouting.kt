package dev.programadorthi.full.stack.server.app.routing

import dev.programadorthi.full.stack.server.app.infrastructure.Resources
import dev.programadorthi.full.stack.server.app.plugins.brandFacade
import dev.programadorthi.full.stack.server.app.plugins.catalogFacade
import dev.programadorthi.full.stack.server.app.plugins.typeFacade
import dev.programadorthi.models.CatalogItem
import dev.programadorthi.models.PaginatedItem
import dev.programadorthi.models.PaginationRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

private val LAST_MODIFIED_DATE_FORMAT = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z")

internal fun Route.catalogRouting() {
    route("/items") {
        delete("{id}") {
            removeCatalog()
        }

        get {
            getAllItems()
        }

        post {
            addCatalog()
        }

        put {
            updateCatalog()
        }

        get("{id}") {
            getItemsById()
        }

        get("/type/{typeId}/brand/{brandId}") {
            getItemsByTypeAndBrand()
        }

        route("/by") {
            get {
                getItemsByIds()
            }

            get("{name}") {
                getItemsByName()
            }

            get("/brand/{id}") {
                getItemsByBrand()
            }

            get("/type/{id}") {
                getItemsByType()
            }
        }
    }

    get("/images/{name}") {
        provideImage()
    }

    get("/brands") {
        getAllBrands()
    }

    get("/types") {
        getAllTypes()
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.addCatalog() {
    val catalogItem = call.receive<CatalogItem>()
    val catalogFacade = application.catalogFacade
    catalogFacade.save(catalogItem)
    call.respond(status = HttpStatusCode.Created, message = "")
}

private suspend fun PipelineContext<Unit, ApplicationCall>.updateCatalog() {
    val catalogItem = call.receive<CatalogItem>()
    val catalogFacade = application.catalogFacade
    catalogFacade.update(catalogItem)
    call.respond(status = HttpStatusCode.Created, message = "")
}

private suspend fun PipelineContext<Unit, ApplicationCall>.removeCatalog() {
    val id = call.parameters["id"]
    if (id.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "id is required")
        return
    }
    val catalogFacade = application.catalogFacade
    catalogFacade.remove(id.toInt())
    call.respond(status = HttpStatusCode.NoContent, message = "")
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

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsByIds() {
    val ids = call.request.queryParameters.getAll("id")
    if (ids.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "At least one id is required")
        return
    }
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByIds(ids = ids.map { it.toInt() })
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsById() {
    val id = call.parameters["id"]
    if (id.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "id is required")
        return
    }
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByIds(ids = listOf(id.toInt()))
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsByTypeAndBrand() {
    val typeId = call.parameters["typeId"]
    val brandId = call.parameters["brandId"]
    if (typeId.isNullOrEmpty() || brandId.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "type id and brand id are required")
        return
    }
    val paginationRequest = call.receive<PaginationRequest>()
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByTypeAndBrand(
        typeId = typeId.toInt(),
        brandId = brandId.toInt(),
        request = paginationRequest,
    )
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsByName() {
    val name = call.parameters["name"]
    if (name.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "name is required")
        return
    }
    val paginationRequest = call.receive<PaginationRequest>()
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByNameStartsWith(name = name, request = paginationRequest)
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsByBrand() {
    val id = call.parameters["id"]
    if (id.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "brand id is required")
        return
    }
    val paginationRequest = call.receive<PaginationRequest>()
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByBrand(brandId = id.toInt(), request = paginationRequest)
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getItemsByType() {
    val id = call.parameters["id"]
    if (id.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.BadRequest, message = "type id is required")
        return
    }
    val paginationRequest = call.receive<PaginationRequest>()
    val catalogFacade = application.catalogFacade
    val data = catalogFacade.catalogsByType(typeId = id.toInt(), request = paginationRequest)
    call.respond(data)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.provideImage() {
    val name = call.parameters["name"]
    if (name.isNullOrEmpty()) {
        call.respond(status = HttpStatusCode.NotFound, message = "Invalid image name")
        return
    }
    val image = Resources.image(name)?.file ?: ""
    if (image.isEmpty()) {
        call.respond(status = HttpStatusCode.NotFound, message = "File not found")
        return
    }
    val file = File(image)
    call.response.header(
        HttpHeaders.ContentType,
        "image/webp"
    )
    call.response.header(
        HttpHeaders.LastModified,
        LAST_MODIFIED_DATE_FORMAT.format(Date(file.lastModified()))
    )
    call.respondFile(file)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getAllBrands() {
    val brandFacade = application.brandFacade
    val brands = brandFacade.brands()
    call.respond(brands)
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getAllTypes() {
    val typeFacade = application.typeFacade
    val types = typeFacade.types()
    call.respond(types)
}
