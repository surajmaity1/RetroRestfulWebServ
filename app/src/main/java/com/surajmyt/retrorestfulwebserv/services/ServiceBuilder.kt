package com.surajmyt.retrorestfulwebserv.services

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit


object ServiceBuilder {
    private const val URL = "http://10.0.2.2:7002/"

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Custom Interceptor created
    val headerInterceptor = object: Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {

            var request = chain.request()

            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()

            val response = chain.proceed(request)
            return response
        }
    }

    // Note: If you've slow connection, you can wait for 15 seconds and then show one retry button to refresh
    // otherwise it'll give bad impression to the user interactivity
    private val okHttp = OkHttpClient
        .Builder()
        .callTimeout(5, TimeUnit.SECONDS) // Implement retry button for time-out purpose
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}