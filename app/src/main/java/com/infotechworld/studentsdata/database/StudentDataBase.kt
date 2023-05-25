package com.infotechworld.roomdbdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.infotechworld.studentsdata.dao.StudentDao
import com.infotechworld.studentsdata.model.Student

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDataBase : RoomDatabase() {
    abstract fun contactDao(): StudentDao

    companion object {
        @Volatile
        private var instance: StudentDataBase? = null

        fun roomInstance(context: Context): StudentDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        StudentDataBase::class.java,
                        "StudentDataDB").build()
                }
            }
            return instance!!
        }
    }
}