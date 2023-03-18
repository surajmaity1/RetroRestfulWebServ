package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)

		// To be replaced by retrofit code
		message.text = "Dummy Message"
	}

	fun getStarted(view: View) {
		val intent = Intent(this, StudentListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
