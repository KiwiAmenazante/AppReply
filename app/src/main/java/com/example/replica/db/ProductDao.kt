package com.example.replica.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.replica.models.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<Product>

    @Insert
    suspend fun insertProduct(product: Product)

    @Query("DELETE FROM Product WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)
}