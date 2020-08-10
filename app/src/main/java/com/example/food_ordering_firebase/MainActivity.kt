package com.example.food_ordering_firebase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        var v: VideoView =findViewById(R.id.vv)
        v.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.v1))
        v.start()

        Handler().postDelayed(
            {
                startActivity(Intent(this@MainActivity,Login::class.java))
                finish()
            } ,5000
        )
    }
}
