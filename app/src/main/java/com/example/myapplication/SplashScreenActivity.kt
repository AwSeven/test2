package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {
    var timer: Timer? = null
    var timerTask: TimerTask? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        timer = Timer()
        timerTask = MyTimerTask()
        timer!!.schedule(timerTask, 5000)
    }

    internal inner class MyTimerTask : TimerTask() {
        override fun run() {
            val intent = Intent(this@SplashScreenActivity, AuthMenuActivity::class.java)
            startActivity(intent)
        }
    }
}