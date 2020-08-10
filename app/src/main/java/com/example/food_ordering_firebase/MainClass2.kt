package com.example.food_ordering_firebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom2.view.*


class MainClass2(var ctx: Activity, var name:ArrayList<Food>):RecyclerView.Adapter<MainClass2.viewholder>() {

    inner class viewholder(v:View):RecyclerView.ViewHolder(v){
        var nm=v.lblcus
        var tp=v.txttype
        var del=v.btndel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        var view=ctx.layoutInflater.inflate(R.layout.custom2,parent,false)
        var vh=viewholder(view)
        return vh
    }

    override fun getItemCount(): Int {
        return name.size
    }
    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.nm.text= name[position].name.toString()
        holder.tp.text=name[position].type.toString()
        holder.del.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Food")
            var myref1=myRef.child("${name[position].type.toString()}")
            myref1.child("${name[position].id}").removeValue()
            Toast.makeText(ctx,"Item Deleted Successfully",Toast.LENGTH_LONG).show()
        }
    }
    /*override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitem=ctx.layoutInflater.inflate(R.layout.custom2,null,true)

        listitem.lblcus.text=name[position]
        listitem.btndel.setOnClickListener {
            Toast.makeText(ctx,"${id[position]}",Toast.LENGTH_LONG).show()
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Food")
            var myref1=myRef.child("${type[position]}")
            myref1.child("${id[position]}").removeValue()


        }
        listitem.txttype.text=type[position]





        return listitem
    }*/
}