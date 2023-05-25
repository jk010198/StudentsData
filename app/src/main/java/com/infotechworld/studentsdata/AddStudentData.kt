package com.infotechworld.studentsdata

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.infotechworld.studentsdata.databinding.ActivityAddStudentDataBinding
import com.infotechworld.studentsdata.model.Student
import com.infotechworld.studentsdata.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddStudentData : AppCompatActivity() {

    lateinit var binding: ActivityAddStudentDataBinding
    lateinit var viewModel: StudentViewModel
    lateinit var courseName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_student_data)

        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        val adapter: ArrayAdapter<String> = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.Course_Name))

        binding.editTextCourse.apply {
            this.adapter = adapter
            setSelection(0)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val item: String = parent?.getItemAtPosition(position).toString()
                    courseName = item

                    if (item.equals("CSE")) {
                        setSubjectNames(resources.getStringArray(R.array.CSE))
                    } else if (item.equals("ECE")) {
                        setSubjectNames(resources.getStringArray(R.array.ECE))
                    } else if (item.equals("Mech")) {
                        setSubjectNames(resources.getStringArray(R.array.Mech))
                    } else if (item.equals("Civil")) {
                        setSubjectNames(resources.getStringArray(R.array.Civil))
                    } else {
                        binding.etSubject1.setText("")
                        binding.etSubject2.setText("")
                        binding.etSubject3.setText("")
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        binding.btnSaveData.setOnClickListener{
            val name = binding.etName.text.toString()
            val mark1 = binding.etSubject1Mark.text.toString()
            val mark2 = binding.etSubject2Mark.text.toString()
            val mark3 = binding.etSubject3Mark.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter student name", Toast.LENGTH_LONG).show()
            } else if (courseName.equals("Select Course")) {
                Toast.makeText(this, "Please select course", Toast.LENGTH_LONG).show()
            } else if (mark1.isEmpty()) {
                Toast.makeText(this, "Please enter mark for subject 1", Toast.LENGTH_LONG).show()
            } else if (mark2.isEmpty()) {
                Toast.makeText(this, "Please enter mark for subject 2", Toast.LENGTH_LONG).show()
            } else if (mark3.isEmpty()) {
                Toast.makeText(this, "Please enter mark for subject 3", Toast.LENGTH_LONG).show()
            } else {
                val total = mark1.toInt() + mark2.toInt() + mark3.toInt()
                viewModel.insertStudentData(Student(0,
                    name,
                    courseName,
                    mark1.toInt(),
                    mark2.toInt(),
                    mark3.toInt(),
                    total,
                    (total / 3)))
                onBackPressed()
            }
        }

    }

    private fun setSubjectNames(sub: Array<String>) {
        binding.etSubject1.setText(sub.get(0))
        binding.etSubject2.setText(sub.get(1))
        binding.etSubject3.setText(sub.get(2))
    }

}