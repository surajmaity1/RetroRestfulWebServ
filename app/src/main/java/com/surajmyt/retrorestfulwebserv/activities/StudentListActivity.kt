package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.StudentAdapter
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_list)

		setSupportActionBar(toolbar)
		toolbar.title = title

		fab.setOnClickListener {
			val intent = Intent(this@StudentListActivity, StudentCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()

		loadStudents()
	}

	private fun loadStudents() {

        // To be replaced by retrofit code
		student_recycler_view.adapter = StudentAdapter(SampleData.Students)
    }
}
