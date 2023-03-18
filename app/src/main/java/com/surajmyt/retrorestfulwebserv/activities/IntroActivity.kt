package com.surajmyt.retrorestfulwebserv.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.surajmyt.retrorestfulwebserv.R
import com.surajmyt.retrorestfulwebserv.services.MessageService
import com.surajmyt.retrorestfulwebserv.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_intro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IntroActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_intro)

		val messageService = ServiceBuilder.buildService(MessageService::class.java)
		val requestCall = messageService.fetchMessage("http://10.0.2.2:8003/message")

		requestCall.enqueue(object: Callback<String> {

			override fun onResponse(call: Call<String>, response: Response<String>) {
				if (response.isSuccessful) {
					val msg = response.body()
					msg?.let {
						message.text = msg
					}
				} else {
					Toast.makeText(this@IntroActivity,
						"Failed to retrieve items", Toast.LENGTH_LONG).show()
				}
			}

			override fun onFailure(call: Call<String>, t: Throwable) {
				Toast.makeText(this@IntroActivity,
					"Failed to retrieve items", Toast.LENGTH_LONG).show()
			}

		})
	}

	fun getStarted(view: View) {
		val intent = Intent(this, StudentListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
