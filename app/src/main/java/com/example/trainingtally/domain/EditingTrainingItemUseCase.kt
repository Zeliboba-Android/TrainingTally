package com.example.trainingtally.domain

class EditingTrainingItemUseCase(private val trainingListRepository: TrainingListRepository) {

    fun editingTrainingItem(trainingItem: TrainingItem){
        trainingListRepository.editingTrainingItem(trainingItem)
    }

}