package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.probot.ecommerce_wscubetech.model.ProductModel

class ProductAdapter(val ctx:Context, val productList:ArrayList<ProductModel>):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val product_image = view.findViewById<ImageView>(R.id.product_image)
        val product_name = view.findViewById<TextView>(R.id.product_name)
        val product_price = view.findViewById<TextView>(R.id.product_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.product_row, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataList = productList[position]
        holder.product_image.setImageResource(dataList.img)
        holder.product_name.setText(dataList.name)
        holder.product_price.setText("$ ${dataList.price}")

    }
}
















