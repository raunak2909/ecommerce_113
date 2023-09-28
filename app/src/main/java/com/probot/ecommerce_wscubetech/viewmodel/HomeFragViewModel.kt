package com.probot.ecommerce_wscubetech.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denzcoskun.imageslider.models.SlideModel
import com.probot.ecommerce_wscubetech.model.CategoryModel
import com.probot.ecommerce_wscubetech.repository.AppRepository

class HomeFragViewModel : ViewModel() {
    val appRepository: AppRepository = AppRepository()
    var mutableBannerData : MutableLiveData<List<SlideModel>> = MutableLiveData<List<SlideModel>>()
    var mutableErrorMsg : MutableLiveData<String> = MutableLiveData<String>()

    var mutableCategoryData : MutableLiveData<List<CategoryModel>> = MutableLiveData<List<CategoryModel>>()

    fun getBanners() : MutableLiveData<List<SlideModel>>{
        mutableBannerData = appRepository.getBanners()
        mutableErrorMsg = appRepository.mutableErrorMsg
        return mutableBannerData
    }

    fun getCategories() {
        mutableCategoryData = appRepository.getCategories()
    }




}