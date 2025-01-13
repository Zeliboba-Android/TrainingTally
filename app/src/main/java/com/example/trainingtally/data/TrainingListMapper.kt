package com.example.trainingtally.data

import com.example.trainingtally.domain.Exercise
import com.example.trainingtally.domain.TrainingItem

class TrainingListMapper {

    // Маппинг тренировок
    fun mapEntityToDbModel(trainingItem: TrainingItem) = TrainingItemDbModel(
        id = trainingItem.id,
        name = trainingItem.name,
        date = trainingItem.date,
        description = trainingItem.description
    )

    fun mapDbModelToEntity(
        trainingItemDbModel: TrainingItemDbModel,
        exercises: List<Exercise>
    ) = TrainingItem(
        id = trainingItemDbModel.id,
        name = trainingItemDbModel.name,
        date = trainingItemDbModel.date,
        description = trainingItemDbModel.description,
        exercises = exercises
    )

    // Маппинг упражнений
    fun mapExerciseEntityToDbModel(exercise: Exercise, trainingId: Int) = ExerciseDbModel(
        id = 0, // ID генерируется автоматически
        trainingId = trainingId,
        exerciseName = exercise.exerciseName,
        exerciseApproaches = exercise.approaches,
        exerciseSets = exercise.sets,
        weight = exercise.weight
    )

    fun mapExerciseDbModelToEntity(exerciseDbModel: ExerciseDbModel) = Exercise(
        exerciseName = exerciseDbModel.exerciseName,
        approaches = exerciseDbModel.exerciseApproaches,
        sets = exerciseDbModel.exerciseSets,
        weight = exerciseDbModel.weight
    )
}
