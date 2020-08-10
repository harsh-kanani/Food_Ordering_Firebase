package com.example.food_ordering_firebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom.view.*
import kotlinx.android.synthetic.main.custom2.view.*


class MainClass(var ctx:Activity, var name:ArrayList<Food>,  var uname:String):RecyclerView.Adapter<MainClass.viewholder>() {

    inner class viewholder(v:View):RecyclerView.ViewHolder(v){
        var nm=v.lblfnm
        var tp=v.lblfoodtype
        var qty=v.txtqty
        var ct=v.btnbuy
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainClass.viewholder {
        var view=ctx.layoutInflater.inflate(R.layout.custom,parent,false)
        var vh=viewholder(view)
        return vh
    }
    override fun getItemCount(): Int {
        return name.size
    }
    override fun onBindViewHolder(holder: MainClass.viewholder, position: Int) {
        holder.nm.text= name[position].name.toString()
        holder.tp.text=name[position].type.toString()
        holder.ct.setOnClickListener {
            var cart=Cart(name[position].name.toString(),name[position].type.toString(),name[position].price.toString(),holder.qty.text.toString(),Integer.parseInt(holder.qty.text.toString())*Integer.parseInt(name[position].price.toString()))
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Cart")
            myRef.child("${uname}").child("${name[position].type.toString()}").child("${name[position].id}").setValue(cart)
            Toast.makeText(ctx,"Food Added in cart...Check Your Cart ",Toast.LENGTH_LONG).show()
        }
    }

}