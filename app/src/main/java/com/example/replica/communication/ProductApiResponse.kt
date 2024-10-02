package com.example.replica.communication

import com.example.replica.models.Product

class ProductApiResponse(
    private var title: String,
    private var price: Double,
    private var description: String,
    private var images: List<String>
) {
    fun toProduct(): Product {
        return Product(
            title = title,
            price = price,
            description = description,
            images = images
        )
    }
}

data class ProductTitleApiResponse(
    var title: String
)

data class ProductImageApiResponse(
    var images: List<String>
)