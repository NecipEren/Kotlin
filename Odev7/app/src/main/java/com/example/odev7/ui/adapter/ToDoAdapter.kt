package com.example.odev7.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.odev7.databinding.ItemTodoBinding
import com.example.odev7.data.entity.ToDo

class ToDoAdapter(
    private val onItemClick: (ToDo) -> Unit
) : ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(ToDoDiffCallback()) {

    inner class ToDoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(toDo: ToDo) {
            binding.textViewToDo.text = toDo.name
            binding.root.setOnClickListener {
                onItemClick(toDo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ToDoDiffCallback : DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }
}
