package com.gl4.tp4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.database.entities.ScheduleDao

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private const val DATABASE_NAME = "bus_schedule"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE?.let { return it }
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .createFromAsset("database/bus_schedule.db") // Add this line to create from asset
                .build()

            INSTANCE = instance

            return instance

        }
    }
}