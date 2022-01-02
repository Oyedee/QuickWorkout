package com.example.quickworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        val toolbar: androidx.appcompat.widget.Toolbar =
            findViewById(R.id.toolbar_finish_activity)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true) //set back button

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val finishBtn = findViewById<Button>(R.id.btnFinish)
        finishBtn.setOnClickListener {
            finish()
        }
    }
}