package com.surajmyt.retrorestfulwebserv.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import com.surajmyt.retrorestfulwebserv.models.Student
import com.surajmyt.retrorestfulwebserv.services.ServiceBuilder
import com.surajmyt.retrorestfulwebserv.services.StudentService
import kotlinx.android.synthetic.main.activity_student_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

			val studentService = ServiceBuilder.buildService(StudentService::class.java)
			val requestCall = studentService.addStudent(newStudent)

			requestCall.enqueue(object: Callback<Student> {

				override fun onResponse(call: Call<Student>, response: Response<Student>) {
					if (response.isSuccessful) {
						finish()
						var newlyCreatedStudent = response.body() // will use it for future ref
						Toast.makeText(context, "Item Added Successfully", Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
					}
				}

				override fun onFailure(call: Call<Student>, t: Throwable) {
					Toast.makeText(context, "Failed to add item", Toast.LENGTH_SHORT).show()
				}
			})
		}
	}
}
