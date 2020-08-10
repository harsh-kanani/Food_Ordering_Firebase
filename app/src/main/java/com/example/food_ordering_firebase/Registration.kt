package com.example.food_ordering_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)



        btnreg.setOnClickListener {
            if (txtbdate.text.toString().isBlank() or txtemail.text.toString().isBlank() or txtfnm.text.toString().isBlank() or
                lblunm.text.toString().isBlank() or txtmno.text.toString().isBlank() or txtpwd.text.toString().isBlank()) {
                Toast.makeText(this@Registration,"Input Is Blank",Toast.LENGTH_LONG).show()
            } else {
                // Write a message to the database
                // Write a message to the database



                var user = User(
                    txtfnm.text.toString(),
                    lblunm.text.toString(),
                    txtemail.text.toString(),
                    txtpwd.text.toString(),
                    txtmno.text.toString(),
                    txtbdate.text.toString()

                )
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Users")


                myRef.child(user.unm).setValue(user)
                Toast.makeText(this@Registration,"Successfully registration",Toast.LENGTH_LONG).show()
                startActivity(Intent(this@Registration,Login::class.java))
                /*var db = DataBaseHelper(this)
                var n = db.insert(user)
                if (n > 0)
                    Toast.makeText(this, "Successfully Add", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this, "Not Add", Toast.LENGTH_LONG).show()*/
            }
        }
    }
}
