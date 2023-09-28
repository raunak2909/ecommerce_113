package com.probot.ecommerce_wscubetech.model

data class CartModel(
    var id: String= "",
    var catId: String = "",
    var pDesc: String = "",
    var pName: String = "",
    var pImg: String = "",
    var pTypeColor: String = "",
    var pTypeName: String = "",
    var pTypePrice: String = "",
    var pid: String = "",
    var qty: Int = 0,
    var subCatId: String = "")
