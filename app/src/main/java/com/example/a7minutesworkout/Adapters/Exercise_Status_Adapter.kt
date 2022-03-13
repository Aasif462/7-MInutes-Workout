package com.example.a7minutesworkout.Adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.Model.ExerciseModel
import com.example.a7minutesworkout.R
import com.example.a7minutesworkout.databinding.ItemExerciseStatusBinding

class Exercise_Status_Adapter( context: Context , val items:ArrayList<ExerciseModel>) :
    RecyclerView.Adapter<Exercise_Status_Adapter.ViewHolder>() {


    class ViewHolder(binding: ItemExerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem:TextView = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()

        when{
            model.getSelected() ->{
                holder.tvItem.background = ContextCompat.getDrawable(holder.tvItem.context , R.drawable.selected_exercise_background)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

            model.getCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.tvItem.context , R.drawable.item_background)
                holder.tvItem.setTextColor(Color.parseColor("#1E1E1E"))
            }

            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(holder.tvItem.context , R.drawable.exercise_status)
                holder.tvItem.setTextColor(Color.parseColor("#212121"))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}