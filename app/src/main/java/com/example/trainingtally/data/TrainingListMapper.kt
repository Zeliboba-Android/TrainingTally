package com.example.trainingtally.data

import com.example.trainingtally.domain.TrainingItem

class TrainingListMapper {

    fun mapEntityToDbModel(trainingItem: TrainingItem) = TrainingItemDbModel(
        id = trainingItem.id,
        name = trainingItem.name,
        date = trainingItem.date,
        description = trainingItem.description,
        exerciseName = trainingItem.exerciseName,
        exerciseApproaches = trainingItem.exerciseApproaches,
        exerciseSets = trainingItem.exerciseSets,
        weight = trainingItem.weight
    )

    fun mapDbModelToEntity(trainingItemDbModel: TrainingItemDbModel) = TrainingItem(
        id = trainingItemDbModel.id,
        name = trainingItemDbModel.name,
        date = trainingItemDbModel.date,
        description = trainingItemDbModel.description,
        exerciseName = trainingItemDbModel.exerciseName,
        exerciseApproaches = trainingItemDbModel.exerciseApproaches,
        exerciseSets = trainingItemDbModel.exerciseSets,
        weight = trainingItemDbModel.weight
    )

    fun mapDbModelToListEntity(list: List<TrainingItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}