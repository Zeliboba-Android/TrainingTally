package com.example.trainingtally.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_items")
data class TrainingItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val date: String,
    val description: String,
    val exerciseName: String,
    val exerciseApproaches: String,
    val exerciseSets: String,
    val weight: String
)
