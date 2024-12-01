package com.example.trainingtally.domain

class GetTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {
    fun getTrainingItem(trainingItemId : Int) : TrainingItem {
        return trainingListRepository.getTrainingItem(trainingItemId)
    }
}