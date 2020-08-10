package com.example.food_ordering_firebase

data class Cart(var fnm:String?=null,var type:String?=null,var price:String?=null,var qty:String?=null,var total:Int?=null ) {
    constructor():this("","","","",0)
}