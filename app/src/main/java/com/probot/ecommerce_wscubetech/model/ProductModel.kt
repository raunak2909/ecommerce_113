package com.probot.ecommerce_wscubetech.model

data class ProductModel (

    val pDesc:String = "",
    val pDiscountPer:String = "",
    val pId:String = "",
    val catId:String = "",
    val subCatId:String = "",
    val pImage:ArrayList<String> = ArrayList(),
    val pName:String = "",
    val pPrice:String = "",
    val pType:ArrayList<Map<String, Any>> = ArrayList(),
)

/*
data class TypeModel(
    val type: String = "", //128 GB
    val color: ArrayList<String> = ArrayList(), // ["#000000", "#ffffff"]
    val price: String = "",//"100000"
    val qty: String = "" //"50"
)*/
