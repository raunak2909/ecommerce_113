package com.probot.ecommerce_wscubetech.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.adapter.CategoryAdapter
import com.probot.ecommerce_wscubetech.adapter.ProductAdapter
import com.probot.ecommerce_wscubetech.databinding.FragmentHomeBinding
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.probot.ecommerce_wscubetech.model.ProductModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        // Data Lists
       val categoryList = ArrayList<CategoryModel>()
        categoryList.add(CategoryModel(R.drawable.vegetables,"vegetables"))
        categoryList.add(CategoryModel(R.drawable.tech,"computers"))
        categoryList.add(CategoryModel(R.drawable.phones,"mobile phones"))
        categoryList.add(CategoryModel(R.drawable.shoes,"shoes"))
        categoryList.add(CategoryModel(R.drawable.clothes,"clothes"))
        categoryList.add(CategoryModel(R.drawable.vegetables,"vegetables"))
        categoryList.add(CategoryModel(R.drawable.tech,"computers"))
        categoryList.add(CategoryModel(R.drawable.phones,"mobile phones"))
        categoryList.add(CategoryModel(R.drawable.shoes,"shoes"))
        categoryList.add(CategoryModel(R.drawable.clothes,"clothes"))

       val productList = ArrayList<ProductModel>()
       productList.add(ProductModel(R.drawable.headphones,"Sony Headphones","200"))
       productList.add(ProductModel(R.drawable.mouse,"Logitech mouse","109"))
       productList.add(ProductModel(R.drawable.controller,"Ps5 controller","60"))
       productList.add(ProductModel(R.drawable.ps5,"PS5 console","400"))
       productList.add(ProductModel(R.drawable.laptop,"Hp Omen","2000"))
       productList.add(ProductModel(R.drawable.keyboard,"Logitech keyboard","100"))
       productList.add(ProductModel(R.drawable.headphones,"Sony Headphones","200"))
       productList.add(ProductModel(R.drawable.mouse,"Logitech mouse","109"))
       productList.add(ProductModel(R.drawable.controller,"Ps5 controller","60"))
       productList.add(ProductModel(R.drawable.ps5,"PS5 console","400"))
       productList.add(ProductModel(R.drawable.laptop,"Hp Omen","2000"))
       productList.add(ProductModel(R.drawable.keyboard,"Logitech keyboard","100"))


        // Setting Adapters
        binding.categoryRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecyclerview.adapter = CategoryAdapter(requireContext(),categoryList)


        binding.productRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
        binding.productRecyclerview.adapter = ProductAdapter(requireContext(),productList)


        return binding.root
    }


}