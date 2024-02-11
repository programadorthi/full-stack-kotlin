package dev.programadorthi.models

public data class CategoryItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val pictureUri: String,
    val catalogBrandId: Int,
    val catalogBrand: String,
    val catalogTypeId: Int,
    val catalogType: String,
)