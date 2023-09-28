package com.probot.ecommerce_wscubetech.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.adapter.CategoryAdapter
import com.probot.ecommerce_wscubetech.adapter.ProductAdapter
import com.probot.ecommerce_wscubetech.databinding.FragmentHomeBinding
import com.probot.ecommerce_wscubetech.model.BannerModel
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.probot.ecommerce_wscubetech.model.ProductModel
import com.probot.ecommerce_wscubetech.viewmodel.HomeFragViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val db = Firebase.firestore

        val categoryList = ArrayList<CategoryModel>()


        var bannerList = ArrayList<SlideModel>()
        binding.pgBannerBar.visibility = View.VISIBLE

        val productModel = ArrayList<ProductModel>()



        //ViewModel
        val homeFragViewModel = ViewModelProvider(this)[HomeFragViewModel::class.java]



        //get Category
        homeFragViewModel.getCategories()

        homeFragViewModel.mutableCategoryData.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrEmpty()){
                // Setting Adapters
                binding.categoryRecyclerview.layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,false)
                binding.categoryRecyclerview.adapter = CategoryAdapter(requireContext(),it as ArrayList<CategoryModel>)

            }
        })


        /*val ref = db.collection("categories").get().addOnSuccessListener {
            for (document in it.documents){
                categoryList.add(document.toObject(CategoryModel::class.java)!!)
            }
            // Setting Adapters
            binding.categoryRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            binding.categoryRecyclerview.adapter = CategoryAdapter(requireContext(),categoryList)
        }*/



        homeFragViewModel.getBanners()

        homeFragViewModel.mutableBannerData.observe(viewLifecycleOwner, Observer {
            //bannerList = it as ArrayList<SlideModel>

            if(!it.isNullOrEmpty()){
                binding.pgBannerBar.visibility = View.GONE
                binding.imageSlider.setImageList(it)
            }
        })

        /*db.collection("banners").get().addOnSuccessListener {
            for (document in it.documents){
                var bannerModel = document.toObject(BannerModel::class.java)!!
                bannerList.add(SlideModel(bannerModel.img, bannerModel.name, ScaleTypes.FIT))
            }
            binding.imageSlider.setImageList(bannerList)
        }*/

        db.collection("products").get().addOnSuccessListener {
            for (document in it.documents){
                productModel.add(document.toObject(ProductModel::class.java)!!)
            }
            binding.productRecyclerview.layoutManager = GridLayoutManager(requireContext(),2)
            binding.productRecyclerview.adapter = ProductAdapter(requireContext(),productModel)

        }

        // Data Lists

        /*categoryList.add(CategoryModel(R.drawable.vegetables,"vegetables"))
        categoryList.add(CategoryModel(R.drawable.tech,"computers"))
        categoryList.add(CategoryModel(R.drawable.phones,"mobile phones"))
        categoryList.add(CategoryModel(R.drawable.shoes,"shoes"))
        categoryList.add(CategoryModel(R.drawable.clothes,"clothes"))
        categoryList.add(CategoryModel(R.drawable.vegetables,"vegetables"))
        categoryList.add(CategoryModel(R.drawable.tech,"computers"))
        categoryList.add(CategoryModel(R.drawable.phones,"mobile phones"))
        categoryList.add(CategoryModel(R.drawable.shoes,"shoes"))
        categoryList.add(CategoryModel(R.drawable.clothes,"clothes"))
*/
      /* val productList = ArrayList<ProductModel>()
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
*/






        return binding.root
    }


}