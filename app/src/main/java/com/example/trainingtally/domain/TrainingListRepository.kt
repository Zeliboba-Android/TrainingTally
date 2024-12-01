package com.example.trainingtally.domain

import androidx.lifecycle.LiveData

interface TrainingListRepository {
    suspend fun addTrainingItem(trainingItem: TrainingItem)
    suspend fun editingTrainingItem(trainingItem: TrainingItem)
    suspend fun deleteTrainingItem(trainingItem: TrainingItem)
    suspend fun getTrainingItem(trainingItemId: Int): TrainingItem
    fun getTrainingList(): LiveData<List<TrainingItem>>
}