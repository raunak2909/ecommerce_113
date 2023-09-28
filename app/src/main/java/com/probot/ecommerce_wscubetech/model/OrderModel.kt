package com.probot.ecommerce_wscubetech.model

data class OrderModel(
    var orderId: String= "",
    var userId: String ="",
    var arrCartModel: ArrayList<CartModel> = ArrayList<CartModel>(),
    var totalPrice : Double = 0.0,
    var totalAmt : Double = 0.0,
    var address: String= "",
    var lat: Double=0.0,
    var lng: Double=0.0,
    var status: Int = 0,
    var dateTime: String = "",
)
