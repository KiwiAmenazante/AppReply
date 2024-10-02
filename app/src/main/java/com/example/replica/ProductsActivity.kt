package com.example.replica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.replica.R
import com.example.replica.adapters.ProductAdapter
import com.example.replica.models.Product
import com.example.replica.communication.ApiResponse
import com.example.replica.network.ProductApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

    private lateinit var btnLoad: Button
    private lateinit var etResults: EditText
    private lateinit var rvProducts: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        setSupportActionBar(findViewById(R.id.toolbar))
        btnLoad = findViewById(R.id.btLoadProducts)
        etResults = findViewById(R.id.etProductCount)
        rvProducts = findViewById(R.id.rvProducts)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        btnLoad.setOnClickListener {
            val resultsText = etResults.text.toString()
            if (resultsText.isNotEmpty() && resultsText.all { it.isDigit() }) {
                val results = resultsText.toInt()
                loadProducts(results) { products ->
                    rvProducts.adapter = ProductAdapter(products, this)
                    rvProducts.layoutManager = LinearLayoutManager(this@ProductsActivity)
                }
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadProducts(results: Int, onComplete: (List<Product>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productService: ProductApiService = retrofit.create(ProductApiService::class.java)
        val request = productService.getProducts()

        request.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse: ApiResponse = response.body()!!
                    val productList = apiResponse.results ?: emptyList()
                    println("Products loaded successfully: ${productList.size} items")
                    onComplete(productList)
                } else {
                    println("Response not successful: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    override fun onItemClick(product: Product) {

        Toast.makeText(this, "Product ${product.title} added to cart", Toast.LENGTH_SHORT).show()
    }
}