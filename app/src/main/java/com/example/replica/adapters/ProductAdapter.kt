package com.example.replica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.replica.R
import com.example.replica.models.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<Product>, private val clickListener: OnItemClickListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.productTitle)
        private val tvPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val ivImage: ImageView = itemView.findViewById(R.id.productImage)
        private val addToCart: Button = itemView.findViewById(R.id.btnAddToCart)

        fun bind(product: Product, clickListener: OnItemClickListener) {
            tvTitle.text = product.title
            tvPrice.text = product.price.toString()

            if (!product.images.isNullOrEmpty()) {
                Picasso.get()
                    .load(product.images[0])
                    .into(ivImage)
            }

            addToCart.setOnClickListener {
                clickListener.onItemClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototipe_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], clickListener)
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }
}