package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_change_password.*

class changePassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        btncan.setOnClickListener {
            startActivity(Intent(this@changePassword,Home::class.java))
        }

        var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
        var pass:String?=sp.getString("unm","null")
        //Toast.makeText(this,pass.toString(), Toast.LENGTH_LONG).show()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")

        var myref1=myRef.child("${pass.toString()}")
        // var myref2=myref1.child("unm")

        // myRef.setValue("Hello, World!")
        //myRef.child("1").setValue("Harsh")

        btnchange.setOnClickListener {

            if(txtold.text.toString().equals("") && txtnew.text.toString().equals("")){
                Toast.makeText(this@changePassword,"Input Is Blank",Toast.LENGTH_LONG).show()
            }
            else {
                myref1.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                        try {
                            if (txtold.text.toString().equals("${dataSnapshot.child("password").getValue(String::class.java)}")) {
                                myref1.child("password").setValue(txtnew.text.toString())
                                Toast.makeText(
                                    this@changePassword,
                                    "Password Successfully Change",
                                    Toast.LENGTH_LONG
                                ).show()
                                txtold.setText("")
                                txtnew.setText("")

                            } else {
                                Toast.makeText(
                                    this@changePassword,
                                    "Old Password Is Wrong ",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }catch (e:Exception){

                        }


                        //lblmno.text="Mobile Num.: "+dataSnapshot.child("mno").getValue(String::class.java)

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



    }
}
