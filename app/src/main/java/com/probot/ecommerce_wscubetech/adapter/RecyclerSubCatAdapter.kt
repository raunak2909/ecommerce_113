package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.probot.ecommerce_wscubetech.databinding.SubCatRowBinding
import com.squareup.picasso.Picasso

class RecyclerSubCatAdapter(val context: Context, val arrSubCat: ArrayList<Map<String, String>>) : RecyclerView.Adapter<RecyclerSubCatAdapter.ViewHolder>(){
    class ViewHolder(val binding: SubCatRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SubCatRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrSubCat.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(arrSubCat[position]["img"].toString()).into(holder.binding.imgSubCat)
        holder.binding.txtSubCat.text = arrSubCat[position]["name"]
    }


}