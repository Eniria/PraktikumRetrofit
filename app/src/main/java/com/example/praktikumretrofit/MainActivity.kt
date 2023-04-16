package com.example.praktikumretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikumretrofit.api.ApiService
import com.example.praktikumretrofit.model.ResponseData
import com.example.praktikumretrofit.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var rvUser: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvUser = findViewById(R.id.rv_user)
        initRecyclerView()
        loadDataFromAPI()

    }

    private fun initRecyclerView() {
        rvUser.apply {
            val layoutMan = LinearLayoutManager(this@MainActivity)
            val itemDecoration =DividerItemDecoration(this@MainActivity,layoutMan.orientation)
            layoutManager = layoutMan
            addItemDecoration(itemDecoration)
        }
    }

    private fun loadDataFromAPI() {
        Thread(Runnable {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiService = retrofit.create(ApiService::class.java)
            val client = apiService.getListUsers("2")
            client.enqueue(object : Callback<ResponseData> {
                override fun onResponse(
                    call: Call<ResponseData>,
                    response: Response<ResponseData>
                ) {
                    if (response.isSuccessful) {
                        val bodyResponse = response.body()
                        val listUser = bodyResponse?.listUser as List<User>
                        val userAdapter = UserAdapter(listUser)
                        rvUser.adapter = userAdapter
                    }
                }
                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }).start()
    }
}