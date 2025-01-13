package com.example.trainingtally.domain

class AddTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {

    suspend fun addTrainingItem(trainingItem: TrainingItem){
        trainingListRepository.addTrainingItem(trainingItem)
    }

}