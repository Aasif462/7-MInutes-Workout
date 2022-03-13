package com.example.a7minutesworkout

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.a7minutesworkout.databinding.ExerciseCustomDialogBinding
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.frameLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        frameLayout.setOnClickListener{
            startActivity(Intent(applicationContext , ExerciseActivity::class.java))
            finish()
        }

        bmiBtn.setOnClickListener{
            startActivity(Intent(applicationContext , BmiCalculator::class.java))
        }

    }

}