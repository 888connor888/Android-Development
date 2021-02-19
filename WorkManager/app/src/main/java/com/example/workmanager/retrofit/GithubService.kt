package com.example.workmanager.retrofit

import com.example.workmanager.response.SearchResponse
import com.example.workmanager.response.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id:String): Response<User>

    @GET("search/users")
    suspend fun searchUsers(@Query("q")query:String):Response<SearchResponse>
}