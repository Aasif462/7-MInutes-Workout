package com.example.a7minutesworkout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.Adapters.Exercise_Status_Adapter
import com.example.a7minutesworkout.Model.Constants
import com.example.a7minutesworkout.Model.ExerciseModel
import com.example.a7minutesworkout.databinding.ExerciseCustomDialogBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_exercise.*
import java.util.*
import java.util.zip.CheckedOutputStream
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var timer:CountDownTimer ?= null
    private var restProgress = 0

    private var exerciseTimer:CountDownTimer ?= null
    private var exerciseProgress = 0

    private lateinit var exerciseList:ArrayList<ExerciseModel>
    private var currentExercisePosition = -1

    private lateinit var adapter: Exercise_Status_Adapter
    private lateinit var tts:TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar)
        if(supportActionBar!= null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener {
            customDialogForBackButton()
        }

        exerciseList = Constants.defaultExerciseList()

        setRestView()

        tts = TextToSpeech(this , this)
        ExerciseStatus()
    }



    private fun setRestView(){
        progressBar.visibility = View.VISIBLE
        getReadytxt.visibility = View.VISIBLE
        frameLayout.visibility = View.VISIBLE
        exerciseImage.visibility = View.INVISIBLE
        flExerciseView.visibility = View.INVISIBLE
        exerciseName.visibility = View.INVISIBLE
        textView2.visibility = View.VISIBLE
        upExercise.visibility = View.VISIBLE
        upExercise.setText(exerciseList[currentExercisePosition+1].getName())

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
        textView2.visibility = View.INVISIBLE
        upExercise.visibility = View.INVISIBLE



        if(exerciseTimer != null){
            exerciseTimer!!.cancel()
            exerciseProgress=0
        }

        speakOut(exerciseList[currentExercisePosition].getName())
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

                exerciseList[currentExercisePosition].setSelected(true)
                exerciseList[currentExercisePosition].setCompleted(false)
                adapter.notifyDataSetChanged()
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

            @SuppressLint("NotifyDataSetChanged")
            override fun onFinish() {

                exerciseList[currentExercisePosition].setSelected(false)
                exerciseList[currentExercisePosition].setCompleted(true)
                adapter.notifyDataSetChanged()

                if(currentExercisePosition < exerciseList.size-1){
                    setRestView()
                }
                else{
                    val intent = Intent(applicationContext , FinishActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                Toast.makeText(applicationContext, "Language Not Supported", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun speakOut(text:String){
        tts.speak(text , TextToSpeech.QUEUE_FLUSH , null , "")
    }

    private fun ExerciseStatus(){
        recViewStatus.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL, false)
        adapter = Exercise_Status_Adapter(this , exerciseList)
        recViewStatus.adapter = adapter

    }

    private fun customDialogForBackButton() {
        val dialog:Dialog = Dialog(this)
        val dialogBinding = ExerciseCustomDialogBinding.inflate(layoutInflater)
        dialog.setContentView(R.layout.exercise_custom_dialog)
        dialog.setCanceledOnTouchOutside(false)

        dialogBinding.btnYes.setOnClickListener {
            startActivity(Intent(applicationContext , MainActivity::class.java))
        }
        dialogBinding.btnNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}