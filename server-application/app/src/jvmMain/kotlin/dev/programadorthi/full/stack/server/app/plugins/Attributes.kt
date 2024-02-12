package dev.programadorthi.full.stack.server.app.plugins

import dev.programadorthi.full.stack.server.app.infrastructure.facade.BrandFacade
import dev.programadorthi.full.stack.server.app.infrastructure.facade.CatalogFacade
import dev.programadorthi.full.stack.server.app.infrastructure.facade.TypeFacade
import io.ktor.server.application.*
import io.ktor.util.*

private val BrandFacadeAttributeKey = AttributeKey<BrandFacade>("BrandFacadeAttributeKey")
private val TypeFacadeAttributeKey = AttributeKey<TypeFacade>("TypeFacadeAttributeKey")
private val CatalogFacadeAttributeKey = AttributeKey<CatalogFacade>("CatalogFacadeAttributeKey")

internal var Application.brandFacade: BrandFacade
    get() = attributes[BrandFacadeAttributeKey]
    set(value) {
        attributes.put(BrandFacadeAttributeKey, value)
    }

internal var Application.typeFacade: TypeFacade
    get() = attributes[TypeFacadeAttributeKey]
    set(value) {
        attributes.put(TypeFacadeAttributeKey, value)
    }

internal var Application.catalogFacade: CatalogFacade
    get() = attributes[CatalogFacadeAttributeKey]
    set(value) {
        attributes.put(CatalogFacadeAttributeKey, value)
    }