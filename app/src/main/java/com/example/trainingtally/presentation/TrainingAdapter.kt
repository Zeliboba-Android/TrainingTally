package com.example.trainingtally.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingtally.R
import com.example.trainingtally.domain.TrainingItem

class TrainingAdapter(private val onDeleteClick: (TrainingItem) -> Unit) :
    ListAdapter<TrainingItem, TrainingAdapter.TrainingViewHolder>(TrainingDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.taining_item, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onDeleteClick)
    }

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_training_data)

        fun bind(item: TrainingItem, onDeleteClick: (TrainingItem) -> Unit) {
            nameTextView.text = item.name
            itemView.setOnLongClickListener {
                onDeleteClick(item)
                true
            }
        }
    }
}

class TrainingDiffCallback : DiffUtil.ItemCallback<TrainingItem>() {
    override fun areItemsTheSame(oldItem: TrainingItem, newItem: TrainingItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrainingItem, newItem: TrainingItem): Boolean {
        return oldItem == newItem
    }
}
