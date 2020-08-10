package com.example.food_ordering_firebase

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class Login : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //full Screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

       //supportActionBar?.hide()
       /* val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)
        Toast.makeText(this@Login,formatted,Toast.LENGTH_LONG).show() */

        btnlog.setOnClickListener {

            if (txtmail.text.toString().equals("admin") && txtpass.text.toString().equals("admin")) {
                Toast.makeText(this@Login, "Successfully Login", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@Login,FoodEntry::class.java))
                finish()
            } else {

                // Write a message to the database
                // Write a message to the database
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Users")
                var u=txtmail.text.toString()
                var myref1=myRef.child("$u")
               // var myref2=myref1.child("unm")

                // myRef.setValue("Hello, World!")
                //myRef.child("1").setValue("Harsh")
                myref1.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                        var valu=""

                        if (txtmail.text.toString().equals(dataSnapshot.child("unm").getValue(String::class.java))&&txtpass.text.toString().equals(dataSnapshot.child("password").getValue(String::class.java))){
                            Toast.makeText(this@Login,"Successfully login",Toast.LENGTH_LONG).show()

                            var sp = getSharedPreferences("MySp", Activity.MODE_PRIVATE)
                            var edt: SharedPreferences.Editor = sp.edit();
                            edt.putString("unm","${txtmail.text.toString()}")
                            edt.apply()
                            edt.commit()

                            startActivity(Intent(this@Login,Home::class.java))
                            finish()

                        }
                        else{
                            Toast.makeText(this@Login,"Wrong Username or password ",Toast.LENGTH_LONG).show()
                        }
                        /*if(dataSnapshot.child("password").getValue(String::class.java)==null){
                            Toast.makeText(this@Login,"Wrong Username or password ",Toast.LENGTH_LONG).show()
                        }*/

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



                /*var db = DataBaseHelper(this)
                var n: Int = db.login1(txtmail.text.toString(), txtpass.text.toString())
                if (n == 1) {
                    Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show()

                    startActivity(Intent(this, Home::class.java))
                    finish()
                } else
                    Toast.makeText(this, "Username or Password Wrong", Toast.LENGTH_LONG).show()

                 */
            }
        }
        btnreset.setOnClickListener {
            txtmail.setText("")
            txtpass.setText("")

        }
        txtreglink.setOnClickListener {
            startActivity(Intent(this,Registration::class.java))
        }
    }
}
