package com.example.trainingtally.domain

import androidx.lifecycle.LiveData

interface TrainingListRepository {
    fun addTrainingItem(trainingItem: TrainingItem)
    fun editingTrainingItem(trainingItem: TrainingItem)
    fun deleteTrainingItem(trainingItem: TrainingItem)
    fun getTrainingItem(trainingItemId: Int): TrainingItem
    fun getTrainingList(): LiveData<List<TrainingItem>>
}