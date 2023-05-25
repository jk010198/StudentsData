package com.infotechworld.studentsdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infotechworld.studentsdata.model.Student
import com.infotechworld.studentsdata.repository.StudentRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(private val repo: StudentRepo): ViewModel() {

    init {
        getContacts()
    }

    fun insertStudentData(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertStudent(student)
        }
    }

    fun getContacts(): LiveData<List<Student>> {
        return repo.getAllContacts()
    }

}