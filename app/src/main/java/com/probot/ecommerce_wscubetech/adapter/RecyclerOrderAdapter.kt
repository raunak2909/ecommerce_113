package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.probot.ecommerce_wscubetech.databinding.MyOrderRowBinding
import com.probot.ecommerce_wscubetech.model.OrderModel

class RecyclerOrderAdapter(val context: Context, val arrOrders: ArrayList<OrderModel>)
    : RecyclerView.Adapter<RecyclerOrderAdapter.ViewHolder>() {
    class ViewHolder(val binding: MyOrderRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MyOrderRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrOrders.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtOrderNo.text = "Order no #${arrOrders[position].orderId}"
        holder.binding.txtDate.text = arrOrders[position].dateTime
        var qty = 0
        for (cart in arrOrders[position].arrCartModel){
            qty += cart.qty
        }
        holder.binding.txtTotalQty.text = "QTY $qty"
        holder.binding.txtTotalPrice.text = "Total Price: ${arrOrders[position].totalPrice}"
        holder.binding.txtTotalAmount.text = "Total Amount: ${arrOrders[position].totalAmt}"

        var statusText = ""
        var textColor = context.getColor(android.R.color.holo_green_light)
        if(arrOrders[position].status==1){
            statusText = "Order Placed"
        } else if(arrOrders[position].status==2){
            statusText = "In Process"
            textColor = context.getColor(android.R.color.holo_orange_light)
        } else if(arrOrders[position].status==3){
            statusText = "In Transit"
        } else if(arrOrders[position].status==4){
            statusText = "Order Cancelled"
            textColor = context.getColor(android.R.color.holo_red_dark)
        } else if(arrOrders[position].status==5){
            statusText = "Order Delivered"
        } else if(arrOrders[position].status==6){
            statusText = "Refunded"
        } else if(arrOrders[position].status==7){
            statusText = "Payment Failed"
            textColor = context.getColor(android.R.color.holo_red_dark)
        }

        holder.binding.txtStatus.text = statusText
        holder.binding.txtStatus.setTextColor(textColor)
    }
}