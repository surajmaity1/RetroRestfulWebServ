package com.surajmyt.retrorestfulwebserv.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import com.surajmyt.retrorestfulwebserv.models.Student
import kotlinx.android.synthetic.main.activity_student_create.*

class StudentCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newStudent = Student()
			newStudent.name = et_name.text.toString()
			newStudent.about = et_about.text.toString()
			newStudent.department = et_department.text.toString()

			// To be replaced by retrofit code
			SampleData.addStudent(newStudent)
            finish() // Move back to StudentListActivity
		}
	}
}
