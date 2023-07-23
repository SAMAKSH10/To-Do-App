package com.example.to_do_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background: Thread = object : Thread() {
            override fun run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep((2 * 1000).toLong())

                    // After 5 seconds redirect to another intent
                    val i = Intent(baseContext, SignUp::class.java)
                    startActivity(i)

                    //Remove activity
                    finish()
                } catch (_: Exception) {
                }
            }
        }
        // start thread
        background.start()
    }
}