package com.example.trainingtally.domain

data class TrainingItem(
    val name: String,
    val date: String,
    val description: String,
    val exerciseName: String,
    val exerciseApproaches: String,
    val exerciseSets: String,
    val weight: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        var UNDEFINED_ID = -1
    }
}
