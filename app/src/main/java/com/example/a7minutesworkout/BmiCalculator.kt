package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise.toolbar
import java.math.BigDecimal
import java.math.RoundingMode

class BmiCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        setSupportActionBar(toolbar)
        if(supportActionBar!= null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        calculate.setOnClickListener {
                if(weight.text.toString().isNotEmpty() && height.text.toString().isEmpty()){
                    if(validateMetricUnit()){
                        val weights:Float = weight.text.toString().toFloat()
                        val heights:Float = height.text.toString().toFloat()/100
                        val bmi = weights / (heights/heights)
                        bmiResult(bmi)
                }
                else{
                    Toast.makeText(applicationContext, "Enter Valid Values", Toast.LENGTH_SHORT)
                        .show()
                }

            }
            else{
                Toast.makeText(applicationContext, "Please Enter Valid Value", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validateMetricUnit():Boolean{
        var isValid = true
        if(weight.text.toString() == null){
            isValid = false
        }
        else if(height.text.toString() == null) {
            isValid = false
        }
        return isValid
    }

    private fun bmiResult(bmi:Float){
        yourBmiTxt.visibility = View.VISIBLE
        bmiValue.visibility = View.VISIBLE
        result.visibility = View.VISIBLE
        msg.visibility = View.VISIBLE

        val bmiLabel:String
        val bmiDescription:String

        if(bmi.compareTo(15f) <= 0){
            bmiLabel = "Very Severely Underweight"
            bmiDescription = "Oops! You Really need to take care of Yourself ! Eat Good"
        }
        else if(bmi.compareTo(15f) >0 && bmi.compareTo(16f) <=0){
            bmiLabel = "Severely Underweight"
            bmiDescription = "Oops! You Really need to take care of Yourself ! Eat Good"
        }
        else if(bmi.compareTo(16f) >0 && bmi.compareTo(18.5f) <=0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You Really need to take care of Yourself ! Eat Good"
        }

        else if(bmi.compareTo(18.5f) >0 && bmi.compareTo(25f) <=0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations You are in Good Shape"
        }

        else if(bmi.compareTo(25f) >0 && bmi.compareTo(30f) <=0){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You Really need to take care of Yourself! Workout Good"
        }

        else if(bmi.compareTo(30f) >0 && bmi.compareTo(35f) <=0){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You Really need to take care of Yourself ! Workout Good"
        }

        else if(bmi.compareTo(35f) >0 && bmi.compareTo(40f) <=0){
            bmiLabel = "Obese Class | (Severely obese)"
            bmiDescription = "Oops! You are in Dangerous Situation Act Now!"
        }

        else {
            bmiLabel = "Obese Class | (Very Severely obese)"
            bmiDescription = "Oops! You are in Dangerous Situation Act Now!"
        }

        val bmiValues = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        bmiValue.setText(bmiValues)
        result.setText(bmiLabel)
        msg.setText(bmiDescription)
    }
}