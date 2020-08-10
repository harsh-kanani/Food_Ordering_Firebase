package com.example.food_ordering_firebase

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_food_entry.*


class FoodEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_entry)

        setSupportActionBar(findViewById(R.id.appBar))

        Toast.makeText(this@FoodEntry,"Food Entry", Toast.LENGTH_LONG).show()

        var list= arrayOf("Select...","Chinese","Maxican","Punjabi","Gujarati","South-Indian")
        var ad=ArrayAdapter<String>(this@FoodEntry,android.R.layout.simple_spinner_dropdown_item,list)
        spftype.adapter=ad

        btnadd.setOnClickListener {
            var food=Food(txtid.text.toString(),txtnm.text.toString(),Integer.parseInt(txtfprice.text.toString()),spftype.selectedItem.toString())
            // Write a message to the database
            // Write a message to the database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("Food")



            myRef.child("${food.type}").child("${food.id}").setValue(food)
            Toast.makeText(this@FoodEntry,"Successfully Add",Toast.LENGTH_LONG).show()



        }
        btnres.setOnClickListener {
            //startActivity(Intent(this@FoodEntry,AdminHome::class.java))
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.adminmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.menushow ->{
                startActivity(Intent(this@FoodEntry,showEntry::class.java))
                true
            }
            R.id.menulg ->{
                startActivity(Intent(this@FoodEntry,Login::class.java))
                Toast.makeText(this@FoodEntry,"Logout",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
