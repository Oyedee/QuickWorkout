package com.example.quickworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress: Int = 0
    private var exerciseProgressDuration: Long = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        val toolbar: androidx.appcompat.widget.Toolbar =
            findViewById(R.id.toolbar_exercise_activity)
        toolbar.navigationIcon?.setTint(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        setUpRestTimer()
    }

    override fun onDestroy() {

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setRestProgressBar() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tvTimer: TextView = findViewById(R.id.tvTimer)

        progressBar.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                setupExerciseTimer()
            }

        }.start()
    }

    private fun setUpRestTimer() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setExerciseProgressBar() {
        val exerciseProgressBar: ProgressBar = findViewById(R.id.exerciseProgressBar)
        val exerciseTvTimer: TextView = findViewById(R.id.tvExerciseTimer)

        exerciseProgressBar.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseProgressDuration * 1000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                exerciseProgressBar.progress = exerciseProgressDuration.toInt() - restProgress
                exerciseTvTimer.text = (exerciseProgressDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                    this@ExerciseActivity,
                    "Here we will start the next rest screen",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()
    }

    private fun setupExerciseTimer() {

        val restViewLayout = findViewById<LinearLayout>(R.id.llRestView)
        val exerciseViewLayout = findViewById<LinearLayout>(R.id.llExerciseView)

        restViewLayout.visibility = View.GONE
        exerciseViewLayout.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
    }
}