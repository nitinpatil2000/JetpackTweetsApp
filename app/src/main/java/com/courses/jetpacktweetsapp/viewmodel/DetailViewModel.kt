package com.courses.jetpacktweetsapp.viewmodel

import android.util.Printer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courses.jetpacktweetsapp.models.TweetsItem
import com.courses.jetpacktweetsapp.repository.TweetRepository
import com.courses.jetpacktweetsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val tweet: StateFlow<Resource<List<TweetsItem>>>
        get() = repository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "motivation"
            repository.getTweets(category)
        }
    }
}