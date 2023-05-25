package com.infotechworld.studentsdata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_data")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val course_name: String,
    val subject1_mark: Int,
    val subject2_mark: Int,
    val subject3_mark: Int,
    val total_mark: Int,
    val average: Int
)
