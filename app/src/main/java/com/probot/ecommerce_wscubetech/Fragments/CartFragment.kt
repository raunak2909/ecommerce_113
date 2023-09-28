package com.probot.ecommerce_wscubetech.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.adapter.RecyclerCartAdapter
import com.probot.ecommerce_wscubetech.databinding.FragmentCartBinding
import com.probot.ecommerce_wscubetech.model.CartModel


class CartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.recyclerCartView.layoutManager = LinearLayoutManager(requireContext())
        val db = Firebase.firestore

        db
            .collection("users")
            .document("UM52YYnKGiErW2CD1kHI")
            .collection("myCart")
            .get()
            .addOnSuccessListener {

                val arrCartItems = ArrayList<CartModel>()

                for (doc in it.documents) {
                    val model = doc.toObject(CartModel::class.java)!!
                    model.id = doc.id
                    arrCartItems.add(model)
                }

                binding.recyclerCartView.adapter = RecyclerCartAdapter(
                    requireContext(),
                    arrCartItems,
                    db,
                    binding.txtTotalPrice,
                    binding.txtTaxAmount,
                    binding.txtTotalAmount,
                    binding.btnCheckout
                )


            }




        return binding.root
    }


}