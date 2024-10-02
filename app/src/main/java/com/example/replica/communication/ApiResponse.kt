package com.example.replica.communication
import com.example.replica.models.Product

data class ApiResponse(
    val results: List<Product>? = null
)