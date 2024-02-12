package dev.programadorthi.full.stack.server.app.infrastructure.facade

import dev.programadorthi.full.stack.server.app.helper.ImageUrlGenerator
import dev.programadorthi.full.stack.server.app.infrastructure.dbQuery
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Catalog
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Catalogs
import dev.programadorthi.models.CatalogItem
import dev.programadorthi.models.PaginationRequest
import org.jetbrains.exposed.sql.SortOrder

internal interface CatalogFacade {
    suspend fun catalogs(request: PaginationRequest): List<CatalogItem>
    suspend fun count(): Long
}

internal class CatalogFacadeImpl(
    private val imageUrlGenerator: ImageUrlGenerator
) : CatalogFacade {

    private fun mapped(catalog: Catalog): CatalogItem = CatalogItem(
        id = catalog.id.value,
        name = catalog.name,
        description = catalog.description,
        price = catalog.price,
        pictureFileName = catalog.pictureFileName,
        pictureUri = imageUrlGenerator.generate(catalog.id.value),
        availableStock = catalog.availableStock,
        maxStockThreshold = catalog.maxStockThreshold,
        restockThreshold = catalog.restockThreshold,
        brand = BrandFacadeImpl.mapped(catalog.brand),
        type = TypeFacadeImpl.mapped(catalog.type),
    )

    override suspend fun catalogs(request: PaginationRequest): List<CatalogItem> = dbQuery {
        Catalog.all()
            .orderBy(Catalogs.name to SortOrder.ASC)
            .limit(request.pageSize, offset = request.pageSize * request.pageIndex)
            .map(::mapped)
    }

    override suspend fun count(): Long = dbQuery {
        Catalog.count()
    }

}