package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.helpers.SampleData
import com.surajmyt.retrorestfulwebserv.models.Student
import kotlinx.android.synthetic.main.activity_student_detail.*



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

		// To be replaced by retrofit code
		val student = SampleData.getStudentById(id)

		student?.let {
			et_name.setText(student.name)
			et_about.setText(student.about)
			et_department.setText(student.department)

			collapsing_toolbar.title = student.name
		}
	}

	private fun initUpdateButton(id: Int) {

		btn_update.setOnClickListener {

			val name = et_name.text.toString()
			val about = et_about.text.toString()
			val department = et_department.text.toString()

            // To be replaced by retrofit code
            val student = Student()
            student.id = id
            student.name = name
            student.about = about
            student.department = department

            SampleData.updateStudent(student);
            finish() // Move back to StudentListActivity
		}
	}

	private fun initDeleteButton(id: Int) {

		btn_delete.setOnClickListener {

            // To be replaced by retrofit code
            SampleData.deleteStudent(id)
            finish() // Move back to StudentListActivity
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
