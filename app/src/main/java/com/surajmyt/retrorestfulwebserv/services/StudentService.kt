package com.surajmyt.retrorestfulwebserv.services

import com.surajmyt.retrorestfulwebserv.models.Student
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface StudentService {
    @GET("student")
    fun getStudentList(): Call<List<Student>>
}