package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.probot.ecommerce_wscubetech.databinding.ProductTypeRowBinding

class RecyclerProductTypeAdapter(val ctx: Context, val mData: ArrayList<Map<String, Any>>) : RecyclerView.Adapter<RecyclerProductTypeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ProductTypeRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductTypeRowBinding.inflate(LayoutInflater.from(ctx), parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.txtType.text = mData[position]["name"].toString()
        holder.binding.txtPrice.text = mData[position]["price"].toString()
    }
}