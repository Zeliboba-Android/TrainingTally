package com.example.trainingtally.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.trainingtally.domain.TrainingItem
import com.example.trainingtally.domain.TrainingListRepository
import kotlinx.coroutines.runBlocking

class TrainingListRepositoryImpl(application: Application) : TrainingListRepository {

    private val trainingListDao = AppDataBase.getInstance(application).trainingListDao()
    private val mapper = TrainingListMapper()

    override suspend fun addTrainingItem(trainingItem: TrainingItem) {
        val trainingId = trainingListDao.addTrainingItem(mapper.mapEntityToDbModel(trainingItem))
        val exerciseDbModels = trainingItem.exercises.map { mapper.mapExerciseEntityToDbModel(it, trainingId.toString().toInt()) }
        trainingListDao.addExercises(exerciseDbModels)
    }

    override suspend fun editingTrainingItem(trainingItem: TrainingItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrainingItem(trainingItem: TrainingItem) {
        trainingListDao.deleteExercisesForTraining(trainingItem.id)
        trainingListDao.deleteTrainingItem(trainingItem.id)
    }

    override suspend fun getTrainingItem(trainingItemId: Int): TrainingItem {
        val trainingDbModel = trainingListDao.getTrainingItem(trainingItemId)
        val exercisesDbModels = trainingListDao.getExercisesForTraining(trainingItemId)
        val exercises = exercisesDbModels.map { mapper.mapExerciseDbModelToEntity(it) }
        return mapper.mapDbModelToEntity(trainingDbModel, exercises)
    }

    override fun getTrainingList(): LiveData<List<TrainingItem>> = MediatorLiveData<List<TrainingItem>>().apply {
        addSource(trainingListDao.getTrainingList()) { trainingItemsDb ->
            value = trainingItemsDb.map { trainingDbModel ->
                val exercises = runBlocking {
                    trainingListDao.getExercisesForTraining(trainingDbModel.id)
                }.map { mapper.mapExerciseDbModelToEntity(it) }
                mapper.mapDbModelToEntity(trainingDbModel, exercises)
            }
        }
    }
}
