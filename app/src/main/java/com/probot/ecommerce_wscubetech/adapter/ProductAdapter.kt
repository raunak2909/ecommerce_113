package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode.Color
import com.probot.ecommerce_wscubetech.Activities.ProductDetailActivity
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.probot.ecommerce_wscubetech.model.ProductModel
import com.squareup.picasso.Picasso

class ProductAdapter(val ctx: Context, val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val product_image = view.findViewById<ImageView>(R.id.product_image)
        val product_name = view.findViewById<TextView>(R.id.product_name)
        val product_price = view.findViewById<TextView>(R.id.product_price)
        val cardColor1 = view.findViewById<CardView>(R.id.cardColor1)
        val cardColor2 = view.findViewById<CardView>(R.id.cardColor2)
        val cardColor3 = view.findViewById<CardView>(R.id.cardColor3)
        val productCard = view.findViewById<CardView>(R.id.productCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.product_row, parent, false))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataList = productList[position]
        Picasso.get().load(dataList.pImage[0]).into(holder.product_image)
        holder.product_name.setText(dataList.pName)
        holder.product_price.setText("\u20B9 ${dataList.pPrice}")

        Log.d("Res", dataList.pType.toString())

        //val colors = (dataList.pType["256 GB"] as Map<String, Any>)["color"] as List<String>

        holder.productCard.setOnClickListener {
            Log.d("pid", dataList.toString())
            ctx.startActivity(
                Intent(ctx, ProductDetailActivity::class.java).putExtra(
                    "pid",
                    dataList.pId
                )
            )
        }

        /*if (colors.size >= 3) {
            holder.cardColor1.setBackgroundColor(android.graphics.Color.parseColor(colors[0]))
            holder.cardColor2.setBackgroundColor(android.graphics.Color.parseColor(colors[1]))
            holder.cardColor3.setBackgroundColor(android.graphics.Color.parseColor(colors[2]))
        } else if (colors.size == 2) {
            holder.cardColor1.visibility = View.GONE
            holder.cardColor3.setBackgroundColor(android.graphics.Color.parseColor(colors[0]))
            holder.cardColor2.setBackgroundColor(android.graphics.Color.parseColor(colors[1]))
        } else {
            holder.cardColor1.visibility = View.GONE
            holder.cardColor2.visibility = View.GONE
            holder.cardColor3.setBackgroundColor(android.graphics.Color.parseColor(colors[0]))
        }*/
    }
}
















