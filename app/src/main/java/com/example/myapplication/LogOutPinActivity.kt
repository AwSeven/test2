package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class LogOutPinActivity : AppCompatActivity() {
    var i = 0
    var value = ""
    lateinit var imageView1: ImageView
    lateinit var imageView2: ImageView
    lateinit var imageView3: ImageView
    lateinit var imageView4: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logoutpin)
        imageView1 = findViewById(R.id.img1)
        imageView2 = findViewById(R.id.img2)
        imageView3 = findViewById(R.id.img3)
        imageView4 = findViewById(R.id.img4)
        val button1 = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)
        val button3 = findViewById<Button>(R.id.btn3)
        val button4 = findViewById<Button>(R.id.btn4)
        val button5 = findViewById<Button>(R.id.btn5)
        val button6 = findViewById<Button>(R.id.btn6)
        val button7 = findViewById<Button>(R.id.btn7)
        val button8 = findViewById<Button>(R.id.btn8)
        val button9 = findViewById<Button>(R.id.btn9)
        val button10 = findViewById<Button>(R.id.Close)
        button10.setOnClickListener {
            finishAffinity()
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> value += "1"
            R.id.btn2 -> value += "2"
            R.id.btn3 -> value += "3"
            R.id.btn4 -> value += "4"
            R.id.btn5 -> value += "5"
            R.id.btn6 -> value += "6"
            R.id.btn7 -> value += "7"
            R.id.btn8 -> value += "8"
            R.id.btn9 -> value += "9"
        }
        i++
        when (i) {
            1 -> imageView1.setImageResource(R.drawable.progfull)
            2 -> imageView2.setImageResource(R.drawable.progfull)
            3 -> imageView3.setImageResource(R.drawable.progfull)
            4 -> {
                imageView4.setImageResource(R.drawable.progfull)
                val intent = Intent(this, AddressActivity::class.java)
                startActivity(intent)
            }
        }
    }
}