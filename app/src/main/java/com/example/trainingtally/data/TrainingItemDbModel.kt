package com.example.trainingtally.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_items")
data class TrainingItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val date: String,
    val description: String
)

@Entity(tableName = "exercises")
data class ExerciseDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val trainingId: Int, // Внешний ключ, связывающий упражнение с тренировкой
    val exerciseName: String,
    val exerciseApproaches: String,
    val exerciseSets: String,
    val weight: String
)