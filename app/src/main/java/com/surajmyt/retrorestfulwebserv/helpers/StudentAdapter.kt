package com.surajmyt.retrorestfulwebserv.helpers

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.activities.StudentDetailActivity
import com.surajmyt.retrorestfulwebserv.models.Student

class StudentAdapter(private val studentList: List<Student>) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

		val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {

		holder.student = studentList[position]
		holder.txvStudent.text = studentList[position].name

		holder.itemView.setOnClickListener { v ->
			val context = v.context
			val intent = Intent(context, StudentDetailActivity::class.java)
			intent.putExtra(StudentDetailActivity.ARG_ITEM_ID, holder.student!!.id)

			context.startActivity(intent)
		}
	}

	override fun getItemCount(): Int {
		return studentList.size
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		val txvStudent: TextView = itemView.findViewById(R.id.txv_student)
		var student: Student? = null

		override fun toString(): String {
			return """${super.toString()} '${txvStudent.text}'"""
		}
	}
}
