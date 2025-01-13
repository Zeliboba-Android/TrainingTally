package com.example.trainingtally.domain

data class TrainingItem(
    val name: String,
    val date: String,
    val description: String,
    val exercises: List<Exercise>,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        var UNDEFINED_ID = 0
    }
}
data class Exercise(
    val exerciseName: String,
    val approaches: String,
    val sets: String,
    val weight: String
)
