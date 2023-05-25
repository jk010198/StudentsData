package com.infotechworld.studentsdata.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.infotechworld.studentsdata.R
import com.infotechworld.studentsdata.databinding.StudentRowItemBinding
import com.infotechworld.studentsdata.model.Student
import com.infotechworld.studentsdata.viewmodel.StudentViewModel


class StudentAdapter(val context: Context, val list: List<Student>): RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

    lateinit var subjects: Array<String>

    class MyViewHolder(val binding: StudentRowItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(StudentRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            val pos = list[position]
            this.tvStudentName.text = "Name :- ${pos.name}"
            this.tvCourseName.text = "Course Name :- ${pos.course_name}"
            if (pos.course_name.equals("CSE")) {
                subjects = (context.resources.getStringArray(R.array.CSE))
            } else if (pos.course_name.equals("ECE")) {
                subjects = (context.resources.getStringArray(R.array.ECE))
            } else if (pos.course_name.equals("Mech")) {
                subjects = (context.resources.getStringArray(R.array.Mech))
            } else if (pos.course_name.equals("Civil")) {
                subjects = (context.resources.getStringArray(R.array.Civil))
            }

            this.tvSubject1Mark.text = "${subjects[0]} :- ${pos.subject1_mark}"
            this.tvSubject2Mark.text = "${subjects[1]} :- ${pos.subject2_mark}"
            this.tvSubject3Mark.text = "${subjects[2]} :- ${pos.subject3_mark}"
            this.tvTotalMarks.text = "Total Marks :- ${pos.total_mark}"
            this.tvAvgMark.text = "Average Marks :- ${pos.average}"

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}