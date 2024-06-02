package com.shinjaehun.android_rails_test

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient
    private lateinit var retrofitService: RetrofitService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRetrofit()

        val button = findViewById<Button>(R.id.btnList)
        button.setOnClickListener {
            retrofitService.getUsers().enqueue(object: Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    val result = response.body()
                    Log.d(TAG, "result: $result")
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d(TAG, "failure: $t")

                }
            })

        }

    }

    private fun initRetrofit() {
        Log.d(TAG, "is it working?")
        retrofitClient=RetrofitClient().getInstance()!!
        retrofitService=RetrofitClient().getRetrofitService()!!

    }
}