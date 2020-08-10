package com.example.food_ordering_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_food_entry.*
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(findViewById(R.id.appBar))
        btnlout.setOnClickListener {
            startActivity(Intent(this@Home,Login::class.java))
        }
        btncart.setOnClickListener {
            startActivity(Intent(this@Home,CheckOut::class.java))
        }

       btnpro.setOnClickListener {
           startActivity(Intent(this@Home,profile::class.java))

       }
        btncp.setOnClickListener {
            startActivity(Intent(this@Home,changePassword::class.java))
        }
        btnorder.setOnClickListener {
            startActivity(Intent(this@Home,foodOrder::class.java))
        }

    }
}
