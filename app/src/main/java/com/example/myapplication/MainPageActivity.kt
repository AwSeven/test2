package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        val textView = findViewById<TextView>(R.id.textView)
        val goToProfile = findViewById<ImageButton>(R.id.goToProfile)

        val receivedData = intent.getStringExtra("key")
        if (receivedData != null) {
            textView.text = receivedData
        }
        goToProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

    }
}