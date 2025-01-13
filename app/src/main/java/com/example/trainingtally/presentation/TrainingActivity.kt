package com.example.trainingtally.presentation

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingtally.R
import com.example.trainingtally.data.AppDataBase
import com.example.trainingtally.data.ExerciseDbModel
import com.example.trainingtally.data.TrainingItemDbModel
import com.example.trainingtally.domain.Exercise
import com.example.trainingtally.domain.TrainingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrainingActivity : AppCompatActivity() {
    private lateinit var trainingItemContainer: LinearLayout
    private lateinit var btnAddExercise: Button
    private lateinit var btnSaveWorkout: Button
    private lateinit var etWorkoutName: EditText
    private lateinit var etWorkoutDate: EditText
    private lateinit var etWorkoutDescription: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training)
        // Инициализация элементов
        trainingItemContainer = findViewById(R.id.exerciseList)
        btnAddExercise = findViewById(R.id.btnAddExercise)
        btnSaveWorkout = findViewById(R.id.btnSaveWorkout)
        etWorkoutName = findViewById(R.id.etWorkoutName)
        etWorkoutDate = findViewById(R.id.etWorkoutDate)
        etWorkoutDescription = findViewById(R.id.etWorkoutDescription)
        // Инициализация контейнера и кнопки
        trainingItemContainer = findViewById(R.id.exerciseList)
        btnAddExercise = findViewById(R.id.btnAddExercise)

        // Обработчик нажатия на кнопку
        btnAddExercise.setOnClickListener {
            addTrainingItem()
        }

        btnSaveWorkout.setOnClickListener {
            saveToDatabase()

        }
    }

    // Метод сохранения данных в базу
    private fun saveToDatabase() {
        // Считываем данные о тренировке
        val workoutName = etWorkoutName.text.toString()
        val workoutDate = etWorkoutDate.text.toString()
        val workoutDescription = etWorkoutDescription.text.toString()

        // Проверяем, что обязательные поля заполнены
        if (workoutName.isBlank() || workoutDate.isBlank()) {
            showToast("Введите название и дату тренировки!")
            return
        }

        // Список упражнений
        val exercises = mutableListOf<Exercise>()

        // Проходим по каждому дочернему элементу контейнера
        for (i in 0 until trainingItemContainer.childCount) {
            val childView = trainingItemContainer.getChildAt(i)
            if (childView != null) {
                val etExerciseName = childView.findViewById<EditText>(R.id.et_exercise_name)
                val etExerciseApproaches = childView.findViewById<EditText>(R.id.et_exercise_approaches)
                val etExerciseSets = childView.findViewById<EditText>(R.id.et_exercise_sets)
                val etExerciseWeight = childView.findViewById<EditText>(R.id.et_exercise_weight)

                // Считываем данные о каждом упражнении
                val exerciseName = etExerciseName?.text.toString()
                val exerciseApproaches = etExerciseApproaches?.text.toString()
                val exerciseSets = etExerciseSets?.text.toString()
                val exerciseWeight = etExerciseWeight?.text.toString()

                // Проверяем, что поля упражнения заполнены
                if (exerciseName.isNotBlank() && exerciseApproaches.isNotBlank() &&
                    exerciseSets.isNotBlank() && exerciseWeight.isNotBlank()) {
                    val exercise = Exercise(
                        exerciseName = exerciseName,
                        approaches = exerciseApproaches,
                        sets = exerciseSets,
                        weight = exerciseWeight
                    )
                    exercises.add(exercise)
                }
            }
        }

        // Проверяем, что добавлено хотя бы одно упражнение
        if (exercises.isEmpty()) {
            showToast("Добавьте хотя бы одно упражнение!")
            return
        }
        Log.d("TrainingActivity", "Workout saved: $exercises")

        // Сохраняем тренировку и упражнения в базу данных
        CoroutineScope(Dispatchers.IO).launch {
            val dao = AppDataBase.getInstance(application).trainingListDao()

            val trainingId = dao.getTrainingList().value?.lastOrNull()?.id ?: 0
            val exerciseDbModels = exercises.map { exercise ->
                ExerciseDbModel(
                    id = 0, // ID будет автогенерироваться
                    trainingId = trainingId,
                    exerciseName = exercise.exerciseName,
                    exerciseApproaches = exercise.approaches,
                    exerciseSets = exercise.sets,
                    weight = exercise.weight
                )
            }

            // Сохраняем упражнения
            dao.addExercises(exerciseDbModels)

            withContext(Dispatchers.Main) {
                showToast("Тренировка успешно сохранена!")
                Log.d("TrainingActivity", "Workout saved: $workoutName")
                Log.d("TrainingActivity", "Workout saved: $workoutDate")
                Log.d("TrainingActivity", "Workout saved: $workoutDescription")
                val intent = Intent().apply {
                    putExtra("trainingDate", workoutDate) // Pass the training date
                }
                setResult(RESULT_OK, intent) // Set the result code and data
                finish()
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    // Добавление нового элемента из training_item.xml
    private fun addTrainingItem() {
        val inflater = LayoutInflater.from(this)
        val trainingItemView = inflater.inflate(R.layout.item_exercise, trainingItemContainer, false)
        trainingItemContainer.addView(trainingItemView, trainingItemContainer.childCount - 1)
    }
}