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
import com.probot.ecommerce_wscubetech.adapter.RecyclerOrderAdapter
import com.probot.ecommerce_wscubetech.databinding.FragmentHomeBinding
import com.probot.ecommerce_wscubetech.databinding.FragmentProfileBinding
import com.probot.ecommerce_wscubetech.model.OrderModel


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater,container,false)

        val db = Firebase.firestore

        binding.recyclerOrderView.layoutManager = LinearLayoutManager(requireActivity())

        db.collection("orders").whereEqualTo("userId", "UM52YYnKGiErW2CD1kHI").get().addOnSuccessListener {

            val arrOrders = ArrayList<OrderModel>()
            for (doc in it.documents){
                val eachOrder = doc.toObject(OrderModel::class.java)!!
                eachOrder.orderId = doc.id
                arrOrders.add(eachOrder)
            }

            binding.recyclerOrderView.adapter = RecyclerOrderAdapter(requireContext(), arrOrders)
        }


        return binding.root
    }


}