package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.StudentAdapter
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import com.surajmyt.retrorestfulwebserv.models.Student
import com.surajmyt.retrorestfulwebserv.services.ServiceBuilder
import com.surajmyt.retrorestfulwebserv.services.StudentService
import kotlinx.android.synthetic.main.activity_student_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
		val studentService = ServiceBuilder.buildService(StudentService::class.java)

		val requestCall = studentService.getStudentList()

		requestCall.enqueue(object: Callback<List<Student>> {

			override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
				if (response.isSuccessful) {
					val studentList = response.body()!!
					student_recycler_view.adapter = StudentAdapter(studentList)
				} else if(response.code() == 401) {
					Toast.makeText(this@StudentListActivity,
						"Session expired. Login again.", Toast.LENGTH_LONG).show()
				} else {
					Toast.makeText(this@StudentListActivity, "Failed to retrieve items", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<List<Student>>, t: Throwable) {
				Toast.makeText(this@StudentListActivity, "Error occurred $t", Toast.LENGTH_LONG).show()
				Log.i("OnFailure:",t.toString())

			}
		})

	}
}
