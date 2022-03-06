package com.example.a7minutesworkout.Model

class ExerciseModel(
    private var id:Int,
    private var exerciseName:String,
    private var image:Int,
    private var isCompleted:Boolean,
    private var isSelected:Boolean
)
{

    fun getId():Int{
        return id
    }

    fun setId(id:Int){
        this.id = id
    }

    fun getName():String{
        return exerciseName
    }

    fun setName(name:String){
        this.exerciseName = name
    }

    fun getImage():Int{
        return image
    }

    fun setImage(image:Int){
        this.image = image
    }

    fun getSelected():Boolean{
        return isSelected
    }

    fun setSelected(selected:Boolean){
        this.isSelected = selected
    }

    fun getCompleted():Boolean{
        return isCompleted
    }

    fun setCompleted(completed:Boolean){
        this.isCompleted = completed
    }

}
