package com.example.quickworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonStart = findViewById(R.id.button_start)
        val btnBMI = findViewById<LinearLayout>(R.id.llBMI)

        buttonStart.setOnClickListener {
            startActivity(Intent(this, ExerciseActivity::class.java))
        }
        btnBMI.setOnClickListener {
            startActivity((Intent(this, BMIActivity::class.java)))
        }

    }
}