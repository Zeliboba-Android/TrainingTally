package com.example.trainingtally.domain

import androidx.lifecycle.LiveData

class GetTrainingListUseCase(private val trainingListRepository: TrainingListRepository) {

    fun getTrainingList(): LiveData<List<TrainingItem>>{
        return trainingListRepository.getTrainingList()
    }

}