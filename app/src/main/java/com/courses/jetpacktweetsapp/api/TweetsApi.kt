package com.courses.jetpacktweetsapp.api

import com.courses.jetpacktweetsapp.models.TweetsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsApi {

    @GET("/v3/b/6668708cad19ca34f8779145?meta=false")
    suspend fun getTweets(
        @Header("X-JSON-Path") category: String                      //dynamic header
    ): Response<List<TweetsItem>>


    @GET("/v3/b/6668708cad19ca34f8779145?meta=false")
    @Headers("X-JSON-Path:tweets[*].category")                       //static header
    suspend fun getCategories(): Response<List<String>>

}