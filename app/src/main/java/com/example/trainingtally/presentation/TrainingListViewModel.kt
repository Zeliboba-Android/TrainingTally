package com.example.trainingtally.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trainingtally.data.TrainingListRepositoryImpl
import com.example.trainingtally.domain.AddTrainingItemUseCase
import com.example.trainingtally.domain.DeleteTrainingItemUseCase
import com.example.trainingtally.domain.EditingTrainingItemUseCase
import com.example.trainingtally.domain.GetTrainingListUseCase
import com.example.trainingtally.domain.TrainingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TrainingListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TrainingListRepositoryImpl(application)
    private val getTrainingListUseCase = GetTrainingListUseCase(repository)
    private val addTrainingItemUseCase = AddTrainingItemUseCase(repository)
    private val deleteTrainingItemUseCase = DeleteTrainingItemUseCase(repository)
    private val editingTrainingItemUseCase = EditingTrainingItemUseCase(repository)

    val trainingList: LiveData<List<TrainingItem>> = getTrainingListUseCase.getTrainingList()

    fun addTrainingItem(trainingItem: TrainingItem) {
        viewModelScope.launch {
            repository.addTrainingItem(trainingItem)
        }
    }


    fun deleteTrainingItem(trainingItem: TrainingItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTrainingItemUseCase.deleteTrainingItem(trainingItem)
        }
    }
    fun changeEnableState(trainingItem: TrainingItem){
        viewModelScope.launch {// viewModelScope.launch запускает корутину, которая будет выполняться в фоновом потоке
            val newItem = trainingItem.copy()
            editingTrainingItemUseCase.editingTrainingItem(newItem)
        }

    }
}