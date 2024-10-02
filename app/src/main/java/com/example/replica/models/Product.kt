package com.example.replica.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "price")
    val price: Double?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "images")
    val images: List<String>?
)