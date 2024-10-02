package com.example.replica.network


import com.example.replica.communication.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("products")
    fun getProducts(): Call<ApiResponse>
}