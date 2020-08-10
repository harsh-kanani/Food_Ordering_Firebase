package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_payment.*


class Payment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
        var unm:String?=sp.getString("unm","null")
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Cart")

        btncout.setOnClickListener {
            //var i=0
            Toast.makeText(this@Payment,"Your Order Will Be Deliverd With In 30 Minutes... ", Toast.LENGTH_LONG).show()
           /* val myRef1 = database.getReference("Orders")
            // Read from the database
            // Read from the database
            myRef1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                    val value =dataSnapshot.getValue(String::class.java)!!
                    //Log.d(FragmentActivity.TAG, "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) { // Failed to read value
                    //Log.w(FragmentActivity.TAG,"Failed to read value.", error.toException()
                    )
                }
            })*/
            myRef.child("${unm.toString()}").removeValue()
            startActivity(Intent(this@Payment,Home::class.java))
            finish()
        }
    }
}
