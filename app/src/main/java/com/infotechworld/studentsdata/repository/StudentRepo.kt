package com.infotechworld.studentsdata.repository

import androidx.lifecycle.LiveData
import com.infotechworld.studentsdata.dao.StudentDao
import com.infotechworld.studentsdata.model.Student
import javax.inject.Inject

class StudentRepo @Inject constructor(private val dao: StudentDao) {

    suspend fun insertStudent(student: Student){
        dao.insertStudent(student)
    }

    fun getAllContacts() : LiveData<List<Student>> {
        return dao.getAllStudents()
    }
}