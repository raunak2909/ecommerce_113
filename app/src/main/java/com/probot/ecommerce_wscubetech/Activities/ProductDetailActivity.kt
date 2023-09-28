package com.probot.ecommerce_wscubetech.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.adapter.RecyclerProductTypeAdapter
import com.probot.ecommerce_wscubetech.databinding.ActivityProductDetailBinding
import com.probot.ecommerce_wscubetech.model.ProductModel
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var productModel: ProductModel
    var itemCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        val db = Firebase.firestore

        val pid = intent.getStringExtra("pid")
        Log.d("pid", pid.toString())

        binding.pdTxtQuanity.text = itemCount.toString()

        binding.pdImgAddButton.setOnClickListener {
            itemCount++
            binding.pdTxtQuanity.text = itemCount.toString()
        }

        binding.pdImgMinusButton.setOnClickListener {
            if (itemCount > 1) {
                itemCount--
                binding.pdTxtQuanity.text = itemCount.toString()
            }
        }

        if (pid != "0") {
            db.collection("products")
                .whereEqualTo("pId", pid)
                .get().addOnSuccessListener {
                    Log.d("res", it.size().toString())
                    if (it.size() == 1) {
                        productModel = it.documents[0].toObject(ProductModel::class.java)!!
                        Picasso.get().load(productModel.pImage[0]).placeholder(R.drawable.clothes)
                            .into(binding.pdImgProductImage)

                        binding.pdTxtProductName.text = productModel.pName
                        binding.pdTxtProductPrice.text = productModel.pPrice
                        binding.pdTxtProductDescription.text = productModel.pDesc

                        var arrType = ArrayList<Map<String, String>>()

                        Log.d("ptype", productModel.pType.toString())



                        binding.recyclerProductType.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                        binding.recyclerProductType.adapter =
                            RecyclerProductTypeAdapter(this, productModel.pType)

                    }
                }
        }

        //adding to cart
        binding.CdAddToCartButton.setOnClickListener {

            var cartMap = mapOf<String, Any>(
                "pImg" to productModel.pImage[0],
                "pid" to productModel.pId,
                "catId" to productModel.catId,
                "subCatId" to productModel.subCatId,
                "pName" to productModel.pName,
                "pDesc" to productModel.pDesc,
                "pTypePrice" to productModel.pType[0]["price"].toString(),
                "pTypeName" to productModel.pType[0]["name"].toString(),
                "pTypeColor" to "#000000",
                "qty" to itemCount
            )


            db
                .collection("users")
                .document("UM52YYnKGiErW2CD1kHI")
                .collection("myCart")
                .add(cartMap).addOnSuccessListener {
                    Log.d("Success:", "Item added to cart successfully")
                    super.getOnBackPressedDispatcher().onBackPressed()
                }

        }


        // Info

        // Top Navigation Related views
        binding.pdImgBackButton
        binding.pdImgShareButton
        binding.pdImgLikeButton

        // Product Related views
        binding.pdImgProductImage
        binding.pdTxtProductName
        binding.pdTxtProductPrice
        binding.pdTxtQuanity

        // Color Related views
        binding.pdViewProductColor1
        binding.pdViewProductColor2
        binding.pdViewProductColor3







        setContentView(binding.root)
    }
}