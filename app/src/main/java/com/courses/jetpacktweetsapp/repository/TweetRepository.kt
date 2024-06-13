package com.courses.jetpacktweetsapp.repository

import com.courses.jetpacktweetsapp.api.TweetsApi
import com.courses.jetpacktweetsapp.models.TweetsItem
import com.courses.jetpacktweetsapp.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val tweetsApi: TweetsApi
) {

    private val _categories = MutableStateFlow<Resource<List<String>>>(Resource.Loading())
    val categories = _categories.asStateFlow()

    private val _tweets = MutableStateFlow<Resource<List<TweetsItem>>>(Resource.Loading())
    val tweets = _tweets.asStateFlow()


    suspend fun getCategories(){
        _categories.emit(Resource.Loading())

        val response = tweetsApi.getCategories()
        if(response.isSuccessful && response.body() != null){
            _categories.emit(Resource.Success(response.body()!!))
        }else{
            _categories.emit(Resource.Error(response.message().toString()))
        }
    }

    suspend fun getTweets(category: String){
        _tweets.emit(Resource.Loading())
        val response = tweetsApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() != null){
            _tweets.emit(Resource.Success(response.body()!!))
        }else{
            _tweets.emit(Resource.Error(response.message().toString()))

        }
    }
}