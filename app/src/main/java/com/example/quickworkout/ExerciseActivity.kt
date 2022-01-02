package com.example.quickworkout

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.quickworkout.model.ExerciseModel
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null
    private var restProgress: Int = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress: Int = 0
    private var exerciseProgressDuration: Long = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition: Int = -1

    private lateinit var exerciseImage: ImageView
    private lateinit var exerciseName: TextView

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdapter? = null
    private lateinit var exerciseRecyclerView: RecyclerView

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
        exerciseImage = findViewById(R.id.exerciseImg)
        exerciseName = findViewById(R.id.tvExerciseName)
        exerciseRecyclerView = findViewById(R.id.rvExerciseStatus)

        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()
        setUpRestView()
        setupExerciseStatusRecyclerView()

    }

    override fun onDestroy() {

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null) {
            player!!.stop()
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
                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
    }

    private fun setUpRestView() {
        val restViewLayout = findViewById<LinearLayout>(R.id.llRestView)
        val exerciseViewLayout = findViewById<LinearLayout>(R.id.llExerciseView)

        try {
            player = MediaPlayer.create(applicationContext, R.raw.press_start)
            player!!.isLooping = false
            player!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        restViewLayout.visibility = View.VISIBLE
        exerciseViewLayout.visibility = View.GONE

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
                exerciseProgressBar.progress = exerciseProgressDuration.toInt() - exerciseProgress
                exerciseTvTimer.text =
                    (exerciseProgressDuration.toInt() - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1) {
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setUpRestView()
                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }

    private fun setupExerciseView() {

        val restViewLayout = findViewById<LinearLayout>(R.id.llRestView)
        val exerciseViewLayout = findViewById<LinearLayout>(R.id.llExerciseView)

        restViewLayout.visibility = View.GONE
        exerciseViewLayout.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        setExerciseProgressBar()

        exerciseImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        exerciseName.text = exerciseList!![currentExercisePosition].getName()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language Specified not Supported")
            } else {
                Log.e("TTS", "Initialization failed")
            }
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //setup recyclerview
    private fun setupExerciseStatusRecyclerView() {
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!, this)
        exerciseRecyclerView.adapter = exerciseAdapter
    }
}