package com.probot.ecommerce_wscubetech.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.probot.ecommerce_wscubetech.adapter.CategoryAdapter
import com.probot.ecommerce_wscubetech.model.BannerModel
import com.probot.ecommerce_wscubetech.model.CategoryModel

class AppRepository {

    companion object{
        val TAG = "AppRepository"
        val bannerCollectionPath = "banners"
        val categoryCollectionPath = "categories"

    }

    //firestore
    val firebaseFireStoreDb : FirebaseFirestore = Firebase.firestore

    //Banner model live data
    val mutableBannerData : MutableLiveData<List<SlideModel>> = MutableLiveData<List<SlideModel>>()
    val mutableErrorMsg : MutableLiveData<String> = MutableLiveData<String>()

    //Category model live data
    val mutableCategoryData : MutableLiveData<List<CategoryModel>> = MutableLiveData<List<CategoryModel>>()
    val mutableCategoryErrorMsg : MutableLiveData<String> = MutableLiveData<String>()

    //getting banners
    fun getBanners() : MutableLiveData<List<SlideModel>>{
        firebaseFireStoreDb.collection(bannerCollectionPath).get().addOnSuccessListener {
            Log.d(TAG, "Get Banner: Success!!")
            val bannerList = ArrayList<SlideModel>()
            for (document in it.documents){
                var bannerModel = document.toObject(BannerModel::class.java)!!
                bannerList.add(SlideModel(bannerModel.img, bannerModel.name, ScaleTypes.FIT))
            }
            mutableBannerData.postValue(bannerList)
        }.addOnFailureListener{
            Log.d(TAG, "Get Banner: Failed!!, ${it.message}")
            it.printStackTrace()
            mutableErrorMsg.postValue(it.message)
            mutableBannerData.postValue(ArrayList<SlideModel>())
        }

        return mutableBannerData

    }

    //getting categories
    fun getCategories() : MutableLiveData<List<CategoryModel>>{
        firebaseFireStoreDb.collection(categoryCollectionPath).get().addOnSuccessListener {
            val categoryList = ArrayList<CategoryModel>()

            for (document in it.documents){
                categoryList.add(document.toObject(CategoryModel::class.java)!!)
            }
           mutableCategoryData.postValue(categoryList)
        }
        return mutableCategoryData
    }

}