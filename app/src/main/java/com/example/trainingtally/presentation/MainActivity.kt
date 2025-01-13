package com.example.trainingtally.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingtally.R
import com.example.trainingtally.domain.TrainingItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TrainingListViewModel
    private lateinit var trainingAdapter: TrainingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[TrainingListViewModel::class.java]
        trainingAdapter = TrainingAdapter { trainingItem -> onDeleteTrainingItem(trainingItem) }

        val recyclerView: RecyclerView = findViewById(R.id.rv_training_item)
        recyclerView.adapter = trainingAdapter

        viewModel.trainingList.observe(this) { trainingList ->
            trainingAdapter.submitList(trainingList)  // Обновляем адаптер
        }

        val addButton: FloatingActionButton = findViewById(R.id.button_add_shop_item)
        addButton.setOnClickListener { openTrainingActivity() }
    }

    private fun openTrainingActivity() {
        val intent = Intent(this, TrainingActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE)
        Log.d("MainActivity", "Training list size: ${viewModel.trainingList.value?.size}")
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val trainingName = data?.getStringExtra("trainingDate")
            if (trainingName != null) {
                val trainingItem = TrainingItem(
                    name = trainingName,
                    date = "", // Подставьте правильное значение
                    description = "", // Подставьте правильное значение
                    exercises = emptyList()
                )
                viewModel.addTrainingItem(trainingItem)
            }
        }
    }
    // Удаляет тренировочный элемент
    private fun onDeleteTrainingItem(trainingItem: TrainingItem) {
        viewModel.deleteTrainingItem(trainingItem)
    }


}
