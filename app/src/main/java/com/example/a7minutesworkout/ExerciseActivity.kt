package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.Model.Constants
import com.example.a7minutesworkout.Model.ExerciseModel
import kotlinx.android.synthetic.main.activity_exercise.*
import java.util.zip.CheckedOutputStream

class ExerciseActivity : AppCompatActivity() {

    private var timer:CountDownTimer ?= null
    private var restProgress = 0

    private var exerciseTimer:CountDownTimer ?= null
    private var exerciseProgress = 0

    private lateinit var exerciseList:ArrayList<ExerciseModel>
    private var currentExercisePosition = -1


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

        exerciseList = Constants.defaultExerciseList()

        setRestView()
    }

    private fun setRestView(){
        progressBar.visibility = View.VISIBLE
        getReadytxt.visibility = View.VISIBLE
        frameLayout.visibility = View.VISIBLE
        exerciseImage.visibility = View.INVISIBLE
        flExerciseView.visibility = View.INVISIBLE
        exerciseName.visibility = View.INVISIBLE
        if(timer != null){
            timer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setExerciseView(){

        progressBar.visibility = View.INVISIBLE
        getReadytxt.visibility = View.INVISIBLE
        frameLayout.visibility = View.INVISIBLE
        exerciseImage.visibility = View.VISIBLE
        flExerciseView.visibility = View.VISIBLE
        exerciseName.visibility = View.VISIBLE

        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        exerciseImage.setImageResource(exerciseList[currentExercisePosition].getImage())
        exerciseName.setText(exerciseList[currentExercisePosition].getName())

        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress

        timer  = object:CountDownTimer(3000 , 1000){
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10-restProgress
                timerTxt.text = (10-restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                setExerciseView()
            }

        }.start()

    }

    private fun setExerciseProgressBar(){
        progressBarExercise.progress = exerciseProgress

        exerciseTimer  = object:CountDownTimer(3000 , 1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                progressBarExercise.progress = 30-exerciseProgress
                timerTxtExercise.text = (30-exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList.size-1){
                    setRestView()
                }
                else{
                    Toast.makeText(applicationContext, "Congratulations You have Completed Your Exercise", Toast.LENGTH_SHORT).show()
                }
            }

        }.start()

    }
}