package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_check_out.*


class CheckOut : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)


        var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
        var unm:String?=sp.getString("unm","null")

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cart")
        var myref1=myRef.child("${unm.toString()}")
        var arlst:ArrayList<Cart> = arrayListOf<Cart>()
        fun a(){
            myref1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                    var total=0
                   arlst.clear()
                    //var i=0
                    for(s1 in dataSnapshot.children){
                        for(s2 in s1.children){

                            var value = s2.getValue(Cart::class.java)
                            total+=Integer.parseInt(s2.child("total").value.toString())
                            if(value != null){
                                arlst.add(value)
                            }


                        }
                    }
                    txtgt.text="Grand Total :"+total.toString()
                    var ca=MainClass3(this@CheckOut,arlst)
                    lstcart.adapter=ca


                }

                override fun onCancelled(error: DatabaseError) { // Failed to read value
                    Log.w("Cart", "Failed to read value.", error.toException())
                }
            })

        }

        a()
        btncheck.setOnClickListener {
            startActivity(Intent(this@CheckOut,Payment::class.java))
        }
        btndelcart.setOnClickListener {
            myref1.removeValue()

        }
    }
}
