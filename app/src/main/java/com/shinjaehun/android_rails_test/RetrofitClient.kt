package com.shinjaehun.android_rails_test

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val BASE_URL = "http://192.168.200.36:3000/"
    private var instance: RetrofitClient? = null
    private var retrofitService: RetrofitService? = null
    private val gson = GsonBuilder().setLenient().create()

    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        retrofitService=retrofit.create(RetrofitService::class.java)
    }

    fun getInstance(): RetrofitClient? {
        if(instance==null){
            instance=RetrofitClient()
        }
        return instance
    }

    fun getRetrofitService(): RetrofitService? {
        return retrofitService
    }
}