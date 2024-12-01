package com.example.trainingtally.domain

class DeleteTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {

    fun deleteTrainingItem(trainingItem: TrainingItem) {
        trainingListRepository.deleteTrainingItem(trainingItem)
    }

}