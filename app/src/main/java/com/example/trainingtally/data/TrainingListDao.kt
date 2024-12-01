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
    fun addTrainingItem(trainingItemDbModel: TrainingItemDbModel)

    @Query("SELECT * FROM training_items WHERE id=:trainingItemId LIMIT 1")
    fun getTrainingItem(trainingItemId: Int):TrainingItemDbModel

    @Query("DELETE FROM training_items WHERE id=:trainingItemId")
    fun deleteTrainingItem(trainingItemId: Int)
}