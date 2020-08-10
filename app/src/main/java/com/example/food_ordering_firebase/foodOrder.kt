package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_food_entry.*
import kotlinx.android.synthetic.main.activity_food_order.*


class foodOrder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_order)

        btnct.setOnClickListener {
            startActivity(Intent(this@foodOrder,CheckOut::class.java))
        }

        var list= arrayOf("All","Chinese","Maxican","Punjabi","Gujarati","South-Indian")
        var ad=ArrayAdapter<String>(this@foodOrder,android.R.layout.simple_spinner_dropdown_item,list)
        spinnerType.adapter=ad

        var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
        var unm:String?=sp.getString("unm","null")

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Food")
        var arlist:ArrayList<Food> = arrayListOf<Food>()
        var flag=0
        fun a(){
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    arlist.clear()
                    for(s1 in dataSnapshot.children){
                        // var valu=s1.getValue()
                        //var s=s1.getValue()
                        for (s2 in s1.children){

                            var value=s2.getValue(Food::class.java)
                            if(value != null){
                                arlist.add(value)
                            }
                        }

                    }

                    var ca=MainClass(this@foodOrder,arlist,unm.toString())
                    lst.adapter=ca
                    lst.layoutManager=GridLayoutManager(this@foodOrder,1)
                }

                override fun onCancelled(error: DatabaseError) { // Failed to read value
                    Log.w(
                        "E",
                        "Failed to read value.",
                        error.toException()
                    )
                }
            })

        }
        a()
        btnfl.setOnClickListener {
            var arl:ArrayList<Food> = arrayListOf<Food>()
            if(spinnerType.selectedItem!="All"){

                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Food")
                var myref1=myRef.child("${spinnerType.selectedItem}")

                myref1.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                        arl.clear()
                        for(s1 in dataSnapshot.children){
                            var value=s1.getValue(Food::class.java)
                            if( value != null){
                                arl.add(value)
                            }
                        }

                        var ca=MainClass(this@foodOrder,arl,unm.toString())
                        lst.adapter=ca
                        lst.layoutManager=GridLayoutManager(this@foodOrder,1)
                    }

                    override fun onCancelled(error: DatabaseError) { // Failed to read value
                        Log.w(
                            "E",
                            "Failed to read value.",
                            error.toException()
                        )
                    }
                })

            }
            else{
                a()
            }
        }




    }
}
