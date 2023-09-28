package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.probot.ecommerce_wscubetech.Activities.SubCatActivity
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.squareup.picasso.Picasso

class CategoryAdapter(val ctx:Context, val categoryList:ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageGame)
        val name = view.findViewById<TextView>(R.id.textName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.category_row, parent, false))
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataList = categoryList[position]
        Picasso.get().load(dataList.catImg).into(holder.image)
        holder.name.text = dataList.catName

       holder.itemView.setOnClickListener {
           ctx.startActivity(Intent(ctx, SubCatActivity::class.java).putExtra("catId", dataList.catId))
       }
    }
}
















