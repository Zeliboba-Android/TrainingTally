package com.example.trainingtally.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrainingListDao {
    @Query("SELECT * FROM training_items")
    fun getTrainingList(): LiveData<List<TrainingItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrainingItem(trainingItemDbModel: TrainingItemDbModel)

    @Query("SELECT * FROM training_items WHERE id=:trainingItemId LIMIT 1")
    suspend fun getTrainingItem(trainingItemId: Int):TrainingItemDbModel

    @Query("DELETE FROM training_items WHERE id=:trainingItemId")
    suspend fun deleteTrainingItem(trainingItemId: Int)

    // Методы для работы с упражнениями
    @Query("SELECT * FROM exercises WHERE trainingId=:trainingId")
    suspend fun getExercisesForTraining(trainingId: Int): List<ExerciseDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExercises(exercises: List<ExerciseDbModel>)

    @Query("DELETE FROM exercises WHERE trainingId=:trainingId")
    suspend fun deleteExercisesForTraining(trainingId: Int)
}