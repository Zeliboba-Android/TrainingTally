package com.example.trainingtally.domain

class GetTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {
    suspend fun getTrainingItem(trainingItemId : Int) : TrainingItem {
        return trainingListRepository.getTrainingItem(trainingItemId)
    }
}