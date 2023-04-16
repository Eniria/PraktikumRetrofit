package com.example.praktikumretrofit.api


import com.example.praktikumretrofit.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Users")
    fun  getListUsers(@Query("page") page: String): Call<ResponseData>
}