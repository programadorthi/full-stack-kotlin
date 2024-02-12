package dev.programadorthi.full.stack.server.app.infrastructure.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

internal object Catalogs : IntIdTable() {
    val name = varchar("name", 50).index(customIndexName = "name")
    val description = varchar("description", 1024)
    val price = float("price")
    val pictureFileName = varchar("picture_file_name", 1024)
    val availableStock = integer("available_stock")
    val maxStockThreshold = integer("max_stock_threshold")
    val restockThreshold = integer("restock_threshold")

    val brand = reference("brand", Brands)
    val type = reference("type", Types)
}

internal class Catalog(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Catalog>(Catalogs)

    var name by Catalogs.name
    var description by Catalogs.description
    var price by Catalogs.price
    var pictureFileName by Catalogs.pictureFileName
    var availableStock by Catalogs.availableStock
    var maxStockThreshold by Catalogs.maxStockThreshold
    var restockThreshold by Catalogs.restockThreshold

    var brand by Brand referencedOn Catalogs.brand
    var type by Type referencedOn Catalogs.type
}