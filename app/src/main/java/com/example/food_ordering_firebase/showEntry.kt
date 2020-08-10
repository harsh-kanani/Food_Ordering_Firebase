package com.example.food_ordering_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_food_order.*
import kotlinx.android.synthetic.main.activity_show_entry.*

class showEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_entry)

        setSupportActionBar(findViewById(R.id.appBar))

        var list = arrayOf("All", "Chinese", "Maxican", "Punjabi", "Gujarati", "South-Indian")
        var ad = ArrayAdapter<String>(
            this@showEntry,
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
        spintype.adapter = ad

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Food")
        var arlst: ArrayList<Food> = arrayListOf<Food>()

        fun a() {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    arlst.clear()

                    for (s1 in dataSnapshot.children) {

                        for (s2 in s1.children) {

                            var value = s2.getValue(Food::class.java)
                            if (value != null) {
                                arlst.add(value)
                            }
                        }

                    }


                    var ca = MainClass2(this@showEntry, arlst)
                    list1.adapter = ca
                    list1.layoutManager = GridLayoutManager(this@showEntry, 1)

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
        btnfilter.setOnClickListener {
            if (spintype.selectedItem != "All") {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Food")
                var myref1 = myRef.child("${spintype.selectedItem}")

                var arlist: ArrayList<Food> = arrayListOf<Food>()


                myref1.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        arlist.clear()
                        for (s1 in dataSnapshot.children) {

                            var value = s1.getValue(Food::class.java)
                            if (value != null) {
                                arlist.add(value)
                            }

                        }

                        //Log.d("Data", "Value is: $temp")
                        var ca = MainClass2(this@showEntry, arlist)
                        list1.adapter = ca
                        list1.layoutManager = GridLayoutManager(this@showEntry, 1)

                    }

                    override fun onCancelled(error: DatabaseError) { // Failed to read value
                        Log.w(
                            "E",
                            "Failed to read value.",
                            error.toException()
                        )
                    }
                })
            } else {
                a()
            }
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.adminmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFoodEntry -> {
                startActivity(Intent(this@showEntry, FoodEntry::class.java))
                true
            }
            R.id.menulg -> {
                startActivity(Intent(this@showEntry, Login::class.java))
                Toast.makeText(this@showEntry, "Logout", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}