package dev.programadorthi.full.stack.server.app.infrastructure.facade

import dev.programadorthi.full.stack.server.app.infrastructure.dbQuery
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Type
import dev.programadorthi.full.stack.server.app.infrastructure.tables.Types
import dev.programadorthi.models.CatalogType
import org.jetbrains.exposed.sql.SortOrder

internal interface TypeFacade {
    suspend fun types(): List<CatalogType>
}

internal class TypeFacadeImpl : TypeFacade {

    override suspend fun types(): List<CatalogType> = dbQuery {
        Type.all()
            .orderBy(Types.name to SortOrder.ASC)
            .map(::mapped)
    }

    internal companion object {
        internal fun mapped(type: Type): CatalogType = CatalogType(
            id = type.id.value,
            name = type.name,
        )
    }

}