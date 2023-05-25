package com.infotechworld.studentsdata

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.infotechworld.studentsdata.adapter.StudentAdapter
import com.infotechworld.studentsdata.databinding.ActivityMainBinding
import com.infotechworld.studentsdata.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        binding.recyclerViewStudentData.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        viewModel.getContacts().observe(this, Observer {
            if(it.size > 0){
                binding.tvNoData.visibility = View.GONE
                binding.recyclerViewStudentData.adapter = StudentAdapter(this, it)
            } else {
                binding.tvNoData.visibility = View.VISIBLE
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addStudent -> {
                startActivity(Intent(this, AddStudentData::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}