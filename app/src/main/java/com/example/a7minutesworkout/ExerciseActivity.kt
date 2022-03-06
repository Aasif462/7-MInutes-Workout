package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*
import java.util.zip.CheckedOutputStream

class ExerciseActivity : AppCompatActivity() {

    private var timer:CountDownTimer ?= null
    private var restProgress = 0

    private var exerciseTimer:CountDownTimer ?= null
    private var exerciseProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar)
        if(supportActionBar!= null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        setRestView()
    }

    private fun setRestView(){
        if(timer != null){
            timer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setExerciseView(){

        progressBar.visibility = View.INVISIBLE
        exerciseName.text = "Exercise Name"
        flExerciseView.visibility = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }
        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress

        timer  = object:CountDownTimer(10000 , 1000){
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10-restProgress
                timerTxt.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                setExerciseView()
            }

        }.start()

    }

    private fun setExerciseProgressBar(){
        progressBarExercise.progress = exerciseProgress

        exerciseTimer  = object:CountDownTimer(30000 , 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30-exerciseProgress
                timerTxtExercise.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {

            }

        }.start()

    }
}