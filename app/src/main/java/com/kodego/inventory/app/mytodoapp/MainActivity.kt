package com.kodego.inventory.app.mytodoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.inventory.app.mytodoapp.databinding.ActivityMainBinding
import com.kodego.inventory.app.mytodoapp.db.ToDo
import com.kodego.inventory.app.mytodoapp.db.ToDoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var todoDb : ToDoDatabase
    lateinit var adapter : TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoDb = ToDoDatabase.invoke(this)
        view()

        binding.btnAdd.setOnClickListener(){
            val task:String = binding.etTask.text.toString()
            val toDo = ToDo(task)
            save(toDo)
            adapter.toDo.add(toDo)
            adapter.notifyDataSetChanged()
            Toast.makeText(applicationContext,"Saved!",Toast.LENGTH_LONG).show()
        }

        binding.btnReset.setOnClickListener(){
            reset()
        }
    }

    private fun reset() {
        GlobalScope.launch(Dispatchers.IO) {
            todoDb.getToDos().resetTodo()
            view()
        }
    }

    private fun delete(toDo: ToDo){
        GlobalScope.launch(Dispatchers.IO) {
            todoDb.getToDos().deleteToDo(toDo.id)
            view()
        }
    }

    private fun view() {
        lateinit var toDo : MutableList<ToDo>
        GlobalScope.launch(Dispatchers.IO) {
            toDo = todoDb.getToDos().getAllToDo()

            withContext(Dispatchers.Main){
                adapter = TodoAdapter(toDo)
                binding.myRecycler.adapter = adapter
                binding.myRecycler.layoutManager = LinearLayoutManager(applicationContext)

                adapter.onTaskDelete = { toDo: ToDo, position: Int ->

                    delete(toDo)
                    adapter.toDo.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun save(toDo: ToDo) {
        GlobalScope.launch(Dispatchers.IO) {
            todoDb.getToDos().addToDo(toDo)
        }
    }
}