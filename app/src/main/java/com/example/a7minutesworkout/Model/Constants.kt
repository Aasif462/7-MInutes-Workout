package com.example.a7minutesworkout.Model

import com.example.a7minutesworkout.R

object Constants
{
    fun defaultExerciseList():ArrayList<ExerciseModel>{

        val exerciseList = ArrayList<ExerciseModel>()

        val ex1 = ExerciseModel(
            1 , "Jumping Jacks" , R.drawable.exercise1 , false , false
        )

        val ex2 = ExerciseModel(
            2 , "Wall Sit" , R.drawable.exercise2 , false , false
        )

        val ex3 = ExerciseModel(
            3 , "Push ups" , R.drawable.exercise3 , false , false
        )

        val ex4 = ExerciseModel(
            4 , "Abdominal Crunch" , R.drawable.exercise4 , false , false
        )

        val ex5 = ExerciseModel(
            5 , "Setup On Chair" , R.drawable.exercise5 , false , false
        )

        val ex6 = ExerciseModel(
            6 , "Incline Push ups" , R.drawable.exercise6 , false , false
        )

        val ex7 = ExerciseModel(
            7 , "Knee Push ups" , R.drawable.exercise7 , false , false
        )

        val ex8 = ExerciseModel(
            8 , "Wide Arm Push ups" , R.drawable.exercise8 , false , false
        )

        val ex9 = ExerciseModel(
            9 , "Cobra Stretch" , R.drawable.exercise9 , false , false
        )

        val ex10 = ExerciseModel(
            10 , "Cheast Stretch" , R.drawable.exercise10 , false , false
        )

        val ex11 = ExerciseModel(
            11 , "Squats" , R.drawable.exercise11 , false , false
        )

        val ex12 = ExerciseModel(
            12 , "Side Lying Leg Lift Left" , R.drawable.exercise12 , false , false
        )

        exerciseList.add(ex1)
        exerciseList.add(ex2)
        exerciseList.add(ex3)
        exerciseList.add(ex4)
        exerciseList.add(ex5)
        exerciseList.add(ex6)
        exerciseList.add(ex7)
        exerciseList.add(ex8)
        exerciseList.add(ex9)
        exerciseList.add(ex10)
        exerciseList.add(ex11)
        exerciseList.add(ex12)

        return exerciseList
    }
}