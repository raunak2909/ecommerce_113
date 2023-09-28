package com.probot.ecommerce_wscubetech.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.probot.ecommerce_wscubetech.databinding.CartRowBinding
import com.probot.ecommerce_wscubetech.model.CartModel
import com.probot.ecommerce_wscubetech.model.OrderModel
import com.squareup.picasso.Picasso
import java.util.Calendar

class RecyclerCartAdapter(
    val context: Context,
    val arrCartItems: ArrayList<CartModel>,
    val db: FirebaseFirestore,
    val txtTotalPrice: TextView,
    val txtTaxAmount: TextView,
    val txtTotalAmount: TextView,
    val btnCheckout: Button
) :
    RecyclerView.Adapter<RecyclerCartAdapter.ViewHolder>() {
    class ViewHolder(val binding: CartRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrCartItems.size
    }

    fun updatePriceSheet() : Map<String, Double>{
        var totalPrice: Double = 0.0
        var totalTax: Double = 0.1
        var totalAmount: Double = 0.0

        for (eachItem in arrCartItems){
            totalPrice += eachItem.qty*eachItem.pTypePrice.toDouble()
        }

        totalAmount = totalPrice + totalPrice*totalTax

        return mapOf(
            "totalPrice" to totalPrice,
            "totalTax" to totalPrice*totalTax,
            "totalAmount" to totalAmount
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productName.text = arrCartItems[position].pName
        holder.binding.productDesc.text = arrCartItems[position].pDesc
        holder.binding.productPrice.text = arrCartItems[position].pTypePrice
        holder.binding.pdTxtQuanity.text = arrCartItems[position].qty.toString()
        Picasso.get().load(arrCartItems[position].pImg).into(holder.binding.productImage)

        val mapPrice = updatePriceSheet()

        txtTotalPrice.text = mapPrice["totalPrice"].toString()
        txtTaxAmount.text = (mapPrice["totalTax"]).toString()
        txtTotalAmount.text = mapPrice["totalAmount"].toString()

        btnCheckout.setOnClickListener {
            val calender = Calendar.getInstance()
            val myOrder = OrderModel(
                orderId = "",
                userId = "UM52YYnKGiErW2CD1kHI",
                arrCartModel = arrCartItems,
                totalPrice = mapPrice["totalPrice"]!!.toDouble(),
                totalAmt = mapPrice["totalAmount"]!!.toDouble(),
                address = "wjhvjhvwjhev",
                lat = 27.7364734,
                lng = 72.27483724,
                status = 1,
                dateTime = "${calender.get(Calendar.DATE)}-${calender.get(Calendar.MONTH)+1}-${calender.get(Calendar.YEAR)}"
            )

            db.collection("orders").add(myOrder).addOnSuccessListener {
                Log.d("Order Placed", it.id)

                //empty the cart
                db
                    .collection("users")
                    .document("UM52YYnKGiErW2CD1kHI")
                    .collection("myCart").get().addOnSuccessListener {

                        for(doc in it.documents){
                            db
                                .collection("users")
                                .document("UM52YYnKGiErW2CD1kHI")
                                .collection("myCart").document(doc.id).delete().addOnSuccessListener {
                                    Log.d("Cart item deleted", "Successfully!!")
                                }.addOnFailureListener {
                                    Log.d("Cart item deleted", "Failed!!")
                                }
                        }

                    }
            }

        }

        holder.binding.pdImgAddButton.setOnClickListener {
            arrCartItems[position].qty++
            notifyItemChanged(position)
        }

        holder.binding.pdImgMinusButton.setOnClickListener {
            if (arrCartItems[position].qty > 0) {
                arrCartItems[position].qty--
                if (arrCartItems[position].qty == 0) {
                    db
                        .collection("users")
                        .document("UM52YYnKGiErW2CD1kHI")
                        .collection("myCart")
                        .document(arrCartItems[position].id).delete()
                    arrCartItems.removeAt(position)
                    notifyItemRemoved(position)
                } else {
                    notifyItemChanged(position)
                }
            }
        }
    }
}