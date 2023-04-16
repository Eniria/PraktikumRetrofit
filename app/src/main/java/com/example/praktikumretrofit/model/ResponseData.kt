package com.example.praktikumretrofit.model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @field:SerializedName("page")
    val page: Int?,
    @field:SerializedName("per_page")
    val perPage: Int?,
    @field:SerializedName("total")
    val total: Int?,
    @field:SerializedName("total_page ")
    val  totalPage: Int?,
    @field:SerializedName("data")
    val listUser: List<User>?
)
