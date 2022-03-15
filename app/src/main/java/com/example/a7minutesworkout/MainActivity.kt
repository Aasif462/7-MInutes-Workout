package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        historyBtn.setOnClickListener{
            startActivity(Intent(applicationContext , HistoryActivity::class.java))
        }

    }

}