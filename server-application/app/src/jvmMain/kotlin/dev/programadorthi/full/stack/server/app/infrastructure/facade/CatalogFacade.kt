package dev.programadorthi.full.stack.server.app.infrastructure.facade

import dev.programadorthi.full.stack.server.app.helper.ImageUrlGenerator
import dev.programadorthi.full.stack.server.app.infrastructure.dbQuery
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Brand
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Catalog
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Catalogs
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Type
import dev.programadorthi.models.CatalogItem
import dev.programadorthi.models.PaginationRequest
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and

internal interface CatalogFacade {
    suspend fun catalogs(request: PaginationRequest): List<CatalogItem>
    suspend fun catalogsByIds(ids: List<Int>): List<CatalogItem>
    suspend fun catalogsByBrand(brandId: Int, request: PaginationRequest): List<CatalogItem>
    suspend fun catalogsByNameStartsWith(name: String, request: PaginationRequest): List<CatalogItem>
    suspend fun catalogsByType(typeId: Int, request: PaginationRequest): List<CatalogItem>
    suspend fun catalogsByTypeAndBrand(typeId: Int, brandId: Int, request: PaginationRequest): List<CatalogItem>
    suspend fun count(): Long
    suspend fun save(catalogItem: CatalogItem): CatalogItem
    suspend fun update(catalogItem: CatalogItem): CatalogItem
    suspend fun remove(id: Int)
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
        pictureUri = imageUrlGenerator.generate(catalog.pictureFileName),
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

    override suspend fun catalogsByIds(ids: List<Int>): List<CatalogItem> = dbQuery {
        Catalog.forIds(ids).map(::mapped)
    }

    override suspend fun catalogsByBrand(brandId: Int, request: PaginationRequest): List<CatalogItem> = dbQuery {
        Catalog.find { Catalogs.brand eq brandId }
            .orderBy(Catalogs.name to SortOrder.ASC)
            .limit(request.pageSize, offset = request.pageSize * request.pageIndex)
            .map(::mapped)
    }

    override suspend fun catalogsByNameStartsWith(
        name: String,
        request: PaginationRequest
    ): List<CatalogItem> = dbQuery {
        Catalog.find { Catalogs.name like "${name}%" }
            .orderBy(Catalogs.name to SortOrder.ASC)
            .limit(request.pageSize, offset = request.pageSize * request.pageIndex)
            .map(::mapped)
    }

    override suspend fun catalogsByType(typeId: Int, request: PaginationRequest): List<CatalogItem> = dbQuery {
        Catalog.find { Catalogs.type eq typeId }
            .orderBy(Catalogs.name to SortOrder.ASC)
            .limit(request.pageSize, offset = request.pageSize * request.pageIndex)
            .map(::mapped)
    }

    override suspend fun catalogsByTypeAndBrand(
        typeId: Int,
        brandId: Int,
        request: PaginationRequest
    ): List<CatalogItem> = dbQuery {
        Catalog.find { (Catalogs.type eq typeId) and (Catalogs.brand eq brandId) }
            .orderBy(Catalogs.name to SortOrder.ASC)
            .limit(request.pageSize, offset = request.pageSize * request.pageIndex)
            .map(::mapped)
    }

    override suspend fun count(): Long = dbQuery {
        Catalog.count()
    }

    override suspend fun save(catalogItem: CatalogItem): CatalogItem = dbQuery {
        val brandInstance = Brand[catalogItem.brand.id]
        val typeInstance = Type[catalogItem.type.id]
        val result = Catalog.new {
            name = catalogItem.name
            description = catalogItem.description
            price = catalogItem.price
            pictureFileName = "${id}.webp"
            availableStock = catalogItem.availableStock
            maxStockThreshold = catalogItem.maxStockThreshold
            restockThreshold = catalogItem.restockThreshold

            brand = brandInstance
            type = typeInstance
        }
        mapped(result)
    }

    override suspend fun remove(id: Int) = dbQuery {
        Catalog[id].delete()
    }

    override suspend fun update(catalogItem: CatalogItem): CatalogItem = dbQuery {
        val brandInstance = Brand[catalogItem.brand.id]
        val typeInstance = Type[catalogItem.type.id]
        val catalog = Catalog[catalogItem.id].apply {
            name = catalogItem.name
            description = catalogItem.description
            price = catalogItem.price
            pictureFileName = "${catalogItem.id}.webp"
            availableStock = catalogItem.availableStock
            maxStockThreshold = catalogItem.maxStockThreshold
            restockThreshold = catalogItem.restockThreshold

            brand = brandInstance
            type = typeInstance
        }
        mapped(catalog)
    }

}