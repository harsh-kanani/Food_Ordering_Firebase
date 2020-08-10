package com.example.food_ordering_firebase

data class Food(var id:String?=null,var name:String?=null,var price:Int?=null,var type:String?=null) {
    constructor():this("","",0,"")
}