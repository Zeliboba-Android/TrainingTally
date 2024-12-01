package com.example.trainingtally.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.trainingtally.domain.TrainingItem
import com.example.trainingtally.domain.TrainingListRepository

class TrainingListRepositoryImpl(application: Application) : TrainingListRepository {

    private val trainingListDao = AppDataBase.getInstance(application).trainingListDao()
    private val mapper = TrainingListMapper()

    override suspend fun addTrainingItem(trainingItem: TrainingItem) {
        trainingListDao.addTrainingItem(mapper.mapEntityToDbModel(trainingItem))
    }

    override suspend fun editingTrainingItem(trainingItem: TrainingItem) {
        trainingListDao.addTrainingItem(mapper.mapEntityToDbModel(trainingItem))
    }

    override suspend fun deleteTrainingItem(trainingItem: TrainingItem) {
        trainingListDao.deleteTrainingItem(trainingItem.id)
    }

    override suspend fun getTrainingItem(trainingItemId: Int): TrainingItem {
        val dbModel = trainingListDao.getTrainingItem(trainingItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getTrainingList(): LiveData<List<TrainingItem>> = MediatorLiveData<List<TrainingItem>>().apply {
        addSource(trainingListDao.getTrainingList()){
            value = mapper.mapDbModelToListEntity(it)
    }
    }


}