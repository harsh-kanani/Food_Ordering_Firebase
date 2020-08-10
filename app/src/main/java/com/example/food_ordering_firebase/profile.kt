package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*


class profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnhm.setOnClickListener {
            startActivity(Intent(this@profile,Home::class.java))
        }

        var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
        var pass:String?=sp.getString("unm","null")
       Toast.makeText(this@profile,pass.toString(),Toast.LENGTH_LONG).show()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")

        var myref1=myRef.child("${pass.toString()}")
        // var myref2=myref1.child("unm")

        // myRef.setValue("Hello, World!")
        //myRef.child("1").setValue("Harsh")
        myref1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.


                lblunm.text="User Name: "+dataSnapshot.child("unm").getValue(String::class.java)
                lblname.text="Name : "+dataSnapshot.child("fnm").getValue(String::class.java)
                lblpwd.text="Password : "+dataSnapshot.child("password").getValue(String::class.java)
                lblmail.text="Email : "+dataSnapshot.child("email").getValue(String::class.java)
                lblbdate.text="Birth Date : "+dataSnapshot.child("bdate").getValue(String::class.java)
                lblmno.text="Mobile Num.: "+dataSnapshot.child("mno").getValue(String::class.java)

                /* for(s1 in dataSnapshot.children){
                    // var valu=s1.getValue()
                     var s=s1.getValue()
                     Log.d("Data", "Value is: $s1")
                 }*/
                /*val value =
                    dataSnapshot.getValue(String::class.java)!!*/

            }


            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w(
                    "e",
                    "Failed to read value.",
                    error.toException()
                )
            }
        })


    }
}
