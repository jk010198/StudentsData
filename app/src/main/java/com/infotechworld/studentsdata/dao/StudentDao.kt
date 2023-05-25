package com.infotechworld.studentsdata.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.infotechworld.studentsdata.model.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insertStudent(student: Student)

    @Query("select * from student_data")
    fun getAllStudents(): LiveData<List<Student>>
}