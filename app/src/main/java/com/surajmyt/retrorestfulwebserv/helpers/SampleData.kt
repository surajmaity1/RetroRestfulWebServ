package com.surajmyt.retrorestfulwebserv.helpers

import com.surajmyt.retrorestfulwebserv.models.Student
import java.util.*

object SampleData {

	val Students = ArrayList<Student>()

	private var COUNT = 5

	private var dummy_about = "I am unknown."

	init {
		// Add some sample items
		val newStudent1 = Student()
		newStudent1.id = 1
		newStudent1.name = "Pranay Deb"
		newStudent1.about = dummy_about
		newStudent1.department = "CSE"
		Students.add(newStudent1)

		val newStudent2 = Student()
		newStudent2.id = 2
		newStudent2.name = "Jagzeet Deb"
		newStudent2.about = dummy_about
		newStudent2.department = "ECE"
		Students.add(newStudent2)

		val newStudent3 = Student()
		newStudent3.id = 3
		newStudent3.name = "Makank Pal"
		newStudent3.about = dummy_about
		newStudent3.department = "EE"
		Students.add(newStudent3)

		val newStudent4 = Student()
		newStudent4.id = 4
		newStudent4.name = "Sarbjit Roy"
		newStudent4.about = dummy_about
		newStudent4.department = "EIE"
		Students.add(newStudent4)

		val newStudent5 = Student()
		newStudent5.id = 5
		newStudent5.name = "Silandra Ghosh"
		newStudent5.about = dummy_about
		newStudent5.department = "CE"
		Students.add(newStudent5)

	}

	fun addStudent(item: Student) {
		item.id = COUNT
		Students.add(item)
		COUNT += 1
	}

	fun getStudentById(id: Int): Student? {
		for (i in Students.indices) {
			if (Students[i].id == id) {
				return Students[i]
			}
		}

		return null
	}

	fun deleteStudent(id: Int) {
		var studentToRemove: Student? = null

		for (i in Students.indices) {
			if (Students[i].id == id) {
				studentToRemove = Students[i]
			}
		}

		if (studentToRemove != null) {
			Students.remove(studentToRemove)
		}
	}

	fun updateStudent(student: Student) {
		for (i in Students.indices) {
			if (Students[i].id == student.id) {
				val studentToUpdate = Students[i]

				studentToUpdate.name = student.name
				studentToUpdate.about = student.about
				studentToUpdate.department = student.department
			}
		}
	}
}
