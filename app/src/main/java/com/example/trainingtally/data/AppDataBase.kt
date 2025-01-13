package com.example.trainingtally.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TrainingItemDbModel::class, ExerciseDbModel::class], // Добавьте вторую сущность
    version = 2,
    exportSchema = false
) // Увеличена версия
abstract class AppDataBase : RoomDatabase() {

    abstract fun trainingListDao(): TrainingListDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "train_item.db"

        fun getInstance(application: Application): AppDataBase {// метод для получения единственного объекта бд
            synchronized(LOCK) {// блокировка для предотвращения одновременного обращения к бд из разных потоков
                INSTANCE?.let { // проверка на null для предотвращения повторного создания объекта бд
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration() // Удаление старой базы при изменении схемы
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}