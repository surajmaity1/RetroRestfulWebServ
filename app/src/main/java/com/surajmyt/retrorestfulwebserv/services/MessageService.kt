package com.surajmyt.retrorestfulwebserv.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {

    @GET
    fun fetchMessage(@Url url: String): Call<String>
}