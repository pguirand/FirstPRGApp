package com.pierretest.firstprgapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pierretest.firstprgapp.data.models.SingleGameModel
import com.pierretest.firstprgapp.data.network.ApiResponse
import com.pierretest.firstprgapp.viewmodels.gamesViewModel

@Composable
fun AllGamesScreen(
    viewModel : gamesViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllGames()
    }

    val gameListState by viewModel.listAllGames.collectAsState()

    when(gameListState) {
        is ApiResponse.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(200, 200, 200, 100))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(Color(200, 200, 200, 100))
                        .align(Alignment.Center),
                    color = Color.Red
                )
            }
        }
        is ApiResponse.Success -> {
            val gameList = (gameListState as ApiResponse.Success<List<SingleGameModel>>).data

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(gameList) {game ->
                    GameItem(game) {

                    }
                }
            }
        }

        is ApiResponse.Error -> {
            val errorMessage = (gameListState as ApiResponse.Error).message
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            ) {
                Text(
                    text = errorMessage ?: "Unexpected Error",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)

                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameItem(
    singleGameModel: SingleGameModel, onItemClick : () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Surface(
            onClick = onItemClick
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(200, 220, 240, 255))
            ) {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .fillMaxHeight()
                        .weight(0.4f),
                    painter = rememberAsyncImagePainter(model = singleGameModel.thumbnail),
                    contentDescription ="" )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .weight(0.6f)
                ) {
                    Text(
                        text = singleGameModel.title.toString().trim(),
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = singleGameModel.shortDescription.toString(),
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}