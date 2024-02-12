package dev.programadorthi.full.stack.server.app.infrastructure.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

internal object Brands : IntIdTable() {
    val name = varchar("name", 100)
}

internal class Brand(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Brand>(Brands)

    var name by Brands.name
}