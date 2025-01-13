package com.example.trainingtally.domain

class DeleteTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {

    suspend fun deleteTrainingItem(trainingItem: TrainingItem) {
        trainingListRepository.deleteTrainingItem(trainingItem)
    }

}