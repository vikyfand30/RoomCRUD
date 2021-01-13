package com.example.tooltrackapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tooltrackapp.model.Tools

@Database(entities = [Tools::class], version = 1, exportSchema = false)
abstract class ToolsRoomDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: ToolsRoomDatabase? = null

        fun getDatabase(context: Context): ToolsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToolsRoomDatabase::class.java,
                    "tools_db"
                )
                    .allowMainThreadQueries() //allows Room to executing task in main thread
                    .fallbackToDestructiveMigration() //allows Room to recreate table if no migrations found
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getToolsDao() : ToolsDao
}