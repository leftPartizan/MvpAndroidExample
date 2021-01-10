package com.example.architecturebase

import com.example.architecturebase.network.IPostApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Repository : IRepository {

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .callTimeout(Companion.REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    private val postApi = retrofit.create(IPostApi::class.java)

    override fun getData(): IPostApi {
        return postApi
    }

    companion object {
        private const val REQUEST_TIMEOUT_SECONDS = 5L
    }

}