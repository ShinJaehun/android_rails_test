package com.shinjaehun.android_rails_test

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("users.json")
    fun getUsers(): Call<List<User>>
}