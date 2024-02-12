package dev.programadorthi.full.stack.server.app.infrastructure.facade

import dev.programadorthi.full.stack.server.app.infrastructure.dbQuery
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Brand
import dev.programadorthi.models.CatalogBrand

internal interface BrandFacade {
    suspend fun brands(): List<CatalogBrand>
}

internal class BrandFacadeImpl : BrandFacade {

    override suspend fun brands(): List<CatalogBrand> = dbQuery {
        Brand.all().map(::mapped)
    }

    internal companion object {
        internal fun mapped(brand: Brand): CatalogBrand = CatalogBrand(
            id = brand.id.value,
            name = brand.name,
        )
    }

}