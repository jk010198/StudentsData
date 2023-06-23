package com.infotechworld.studentsdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotechworld.studentsdata.adapter.StudentAdapter
import com.infotechworld.studentsdata.databinding.FragmentStudentDataBinding
import com.infotechworld.studentsdata.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StudentData : Fragment() {

    private var _binding: FragmentStudentDataBinding? = null
    val binding get() = _binding!!
    val viewModel by viewModels<StudentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentStudentDataBinding.inflate(inflater, container, false)

        binding.recyclerViewStudentData.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        viewModel.getContacts().observe(this, Observer {
            binding.recyclerViewStudentData.adapter = context?.let { it1 -> StudentAdapter(it1, it) }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}