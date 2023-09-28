package com.probot.ecommerce_wscubetech.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.probot.ecommerce_wscubetech.R
import com.probot.ecommerce_wscubetech.adapter.RecyclerSubCatAdapter
import com.probot.ecommerce_wscubetech.databinding.ActivitySubCatBinding

class SubCatActivity : AppCompatActivity() {

    lateinit var binding: ActivitySubCatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        val catId = intent.getStringExtra("catId")

        db.collection("subCat").whereEqualTo("catId", catId).get().addOnSuccessListener {

            val arrSubCatData = ArrayList<Map<String, String>>()

            for(doc in it.documents){
                val subCatMap = mapOf<String, String>(
                    "catId" to doc.get("catId").toString(),
                    "subCatId" to doc.get("subCatId").toString(),
                    "img" to doc.get("subCatImg").toString(),
                    "name" to doc.get("subCatName").toString(),
                )

                arrSubCatData.add(subCatMap)
            }

            binding.recyclerSubCatView.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerSubCatView.adapter = RecyclerSubCatAdapter(this, arrSubCatData)

        }

    }
}