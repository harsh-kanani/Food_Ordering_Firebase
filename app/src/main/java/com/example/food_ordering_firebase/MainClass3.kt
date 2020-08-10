package com.example.food_ordering_firebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.custome3.view.*

class MainClass3(var ctx: Activity, var name:ArrayList<Cart>):ArrayAdapter<Cart>(ctx,R.layout.custome3,name) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listitem=ctx.layoutInflater.inflate(R.layout.custome3,null,true)
        listitem.lblcart.text=name[position].fnm.toString()
        listitem.lblcartprice.text=name[position].price
        listitem.lblcartqty.text=name[position].qty
        listitem.lblcarttype.text=name[position].type





        return listitem
    }
}