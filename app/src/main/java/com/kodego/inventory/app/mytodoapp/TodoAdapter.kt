package com.kodego.inventory.app.mytodoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.inventory.app.mytodoapp.databinding.RowItemBinding
import com.kodego.inventory.app.mytodoapp.db.ToDo

class TodoAdapter (var toDo: MutableList<ToDo>): RecyclerView.Adapter<TodoAdapter.ToDoViewHolder>(){

    var onTaskDelete : ((ToDo, Int) -> Unit) ? =null

    inner class ToDoViewHolder(var binding: RowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater,parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.apply {
            tvTask.text = toDo[position].task
            btnDelete.setOnClickListener {
                onTaskDelete?.invoke(toDo[position],position)
            }
        }
    }

    override fun getItemCount(): Int {
        return toDo.size
    }
}