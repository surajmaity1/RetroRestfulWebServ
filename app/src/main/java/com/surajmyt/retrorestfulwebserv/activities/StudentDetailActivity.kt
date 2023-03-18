package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import com.surajmyt.retrorestfulwebserv.models.Student
import com.surajmyt.retrorestfulwebserv.services.ServiceBuilder
import com.surajmyt.retrorestfulwebserv.services.StudentService
import kotlinx.android.synthetic.main.activity_student_detail.*
import retrofit2.Call
import retrofit2.Response
import android.widget.Toast
import retrofit2.Callback

class StudentDetailActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_student_detail)

		setSupportActionBar(detail_toolbar)
		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		val bundle: Bundle? = intent.extras

		if (bundle?.containsKey(ARG_ITEM_ID)!!) {

			val id = intent.getIntExtra(ARG_ITEM_ID, 0)

			loadDetails(id)

			initUpdateButton(id)

			initDeleteButton(id)
		}
	}

	private fun loadDetails(id: Int) {

		val studentService = ServiceBuilder.buildService(StudentService::class.java)
		val requestCall = studentService.getStudent(id)

		requestCall.enqueue(object : Callback<Student> {

			override fun onResponse(call: Call<Student>, response: Response<Student>) {

				if (response.isSuccessful) {
					val student = response.body()
					student?.let {
						et_name.setText(student.name)
						et_about.setText(student.about)
						et_department.setText(student.department)

						collapsing_toolbar.title = student.name
					}
				} else {
					Toast.makeText(
						this@StudentDetailActivity,
						"Failed to retrieve details",
						Toast.LENGTH_SHORT
					).show()
				}
			}

			override fun onFailure(call: Call<Student>, t: Throwable) {
				Toast.makeText(
					this@StudentDetailActivity,
					"Failed to retrieve details $t",
					Toast.LENGTH_SHORT
				).show()
			}
		})

	}

	private fun initUpdateButton(id: Int) {

		btn_update.setOnClickListener {

			val name = et_name.text.toString()
			val about = et_about.text.toString()
			val department = et_department.text.toString()

			val studentService = ServiceBuilder.buildService(StudentService::class.java)
			val requestCall = studentService.updateStudent(id, name, about, department)

			requestCall.enqueue(object: Callback<Student> {

				override fun onResponse(call: Call<Student>, response: Response<Student>) {
					if (response.isSuccessful) {
						finish()
						var updatedStudent = response.body()
						Toast.makeText(this@StudentDetailActivity,
							"Item Updated Successfully", Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(this@StudentDetailActivity,
							"Failed to update item", Toast.LENGTH_SHORT).show()
					}
				}

				override fun onFailure(call: Call<Student>, t: Throwable) {
					Toast.makeText(this@StudentDetailActivity,
						"Failed to update item", Toast.LENGTH_SHORT).show()
				}
			})
		}
	}

	private fun initDeleteButton(id: Int) {

		btn_delete.setOnClickListener {

			val studentService = ServiceBuilder.buildService(StudentService::class.java)
			val requestCall = studentService.deleteStudent(id)

			requestCall.enqueue(object: Callback<Unit> {

				override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
					if (response.isSuccessful) {
						finish()
						Toast.makeText(this@StudentDetailActivity, "Student Data Deleted", Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(this@StudentDetailActivity, "Failed to Delete", Toast.LENGTH_SHORT).show()
					}
				}

				override fun onFailure(call: Call<Unit>, t: Throwable) {
					Toast.makeText(this@StudentDetailActivity, "Failed to Delete", Toast.LENGTH_SHORT).show()
				}
			})

		}
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		val id = item.itemId
		if (id == android.R.id.home) {
			navigateUpTo(Intent(this, StudentListActivity::class.java))
			return true
		}
		return super.onOptionsItemSelected(item)
	}

	companion object {

		const val ARG_ITEM_ID = "item_id"
	}
}
