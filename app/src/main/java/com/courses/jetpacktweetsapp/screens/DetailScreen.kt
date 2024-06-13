package com.courses.jetpacktweetsapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.courses.jetpacktweetsapp.models.TweetsItem
import com.courses.jetpacktweetsapp.util.Resource
import com.courses.jetpacktweetsapp.viewmodel.DetailViewModel

@Composable
fun DetailScreen() {
    val detailViewModel = hiltViewModel<DetailViewModel>()
    val tweet = detailViewModel.tweet.collectAsState()

    when(tweet.value){
        is Resource.Error ->{
            val errorMessage = (tweet.value as  Resource.Error).message
            ErrorMessage(message = errorMessage ?: "An Occurred Error")
        }
        is Resource.Loading -> {
            LoadingIndicator()
        }
        is Resource.Success -> {
            val tweetItem = (tweet.value as Resource.Success<List<TweetsItem>>).data?: emptyList()
            LazyColumn (content = {
                items(tweetItem){ item ->
                    TweetListItem(tweet = item.text)
                }
            })
        }
    }
}

@Composable
fun TweetListItem(tweet:String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(
                elevation = 8.dp,
                ambientColor = Color.Gray,
                shape = RoundedCornerShape(10),
                spotColor =Color.Blue
            ),
        colors = CardDefaults.cardColors(Color.White),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        },
    )

}