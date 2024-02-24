package dev.programadorthi.full.stack.server.app.infrastructure

import dev.programadorthi.full.stack.server.app.infrastructure.serializable.CatalogSerializable
import dev.programadorthi.models.serialization.JsonParser
import dev.programadorthi.full.stack.server.app.infrastructure.tables.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

internal object DatabaseSingleton {

    fun init() {
        val driverClassName = "org.h2.Driver"
        val jdbcURL = "jdbc:h2:file:./build/db"
        val database = Database.connect(jdbcURL, driverClassName)
        transaction(database) {
            // print sql to std-out
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Brands, Types, Catalogs)

            loadFromJson()
        }
    }

    private fun loadFromJson() {
        val resource = Resources.catalogJson()
        val content = resource?.readText() ?: error(">>>> catalog json not found in: $resource")
        val catalogs = JsonParser.instance.decodeFromString<List<CatalogSerializable>>(content)

        Catalogs.deleteAll()
        Brands.deleteAll()
        Types.deleteAll()

        catalogs.map { it.brand }.toSet().forEach { brandName ->
            Brand.new {
                name = brandName
            }
        }

        catalogs.map { it.type }.toSet().forEach { typeName ->
            Type.new {
                name = typeName
            }
        }

        val brandsMap = Brand.all().associateBy { it.name }
        val typesMap = Type.all().associateBy { it.name }
        for (catalog in catalogs) {
            Catalog.new(catalog.id) {
                name = catalog.name
                description = catalog.description
                price = catalog.price
                pictureFileName = "${catalog.id}.webp"
                availableStock = 100
                maxStockThreshold = 200
                restockThreshold = 10

                brand = brandsMap[catalog.brand]!!
                type = typesMap[catalog.type]!!
            }
        }
    }
}