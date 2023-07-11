package com.alselwi.coil.ui.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alselwi.coil.R
import com.alselwi.coil.ads.BannerAds
import com.alselwi.coil.ui.Photos.PhotosViewModel
import com.alselwi.coil.ui.PhotosItem
import com.alselwi.coil.ui.Screen
import com.alselwi.coil.ui.SearchBar
import com.alselwi.coil.ui.bottomBar.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search(
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel(),
){
    val search = viewModel.getPhotos.value
    var isSearching by remember { viewModel.isSearching }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Row() {

                        Card(
                            modifier = Modifier
                                .padding(end = 6.dp)
                                .size(48.dp)
                                .testTag("circle"),
                            shape = CircleShape,
                        ) {

                            Image(
                                painterResource(R.mipmap.ic_logo_coil_3),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            //   Image(ImageBitmap.imageResource(id = R.mipmap.ic_logo_coil_round), contentDescription = null)
                        }
                        SearchBar(
                            modifier = Modifier.padding(end = 4.dp),
                            hint = stringResource(id = R.string.search),

                            ) {
                            viewModel.searchItems(it)
                        }
                    }
                }
            })

    }, modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {

        Surface(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp), Arrangement.Center
            ) {
                BannerAds()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        content = {
                            if (isSearching) {
                                search.photos?.let {
                                    items(it) {
                                        PhotosItem(photo = it,
                                            onUserPhotoClick = { navController.navigate(Screen.UserPhotos.route + "/${it.owner}") }
                                        )
                                    }
                                }
                            }
                        }, contentPadding = PaddingValues(16.dp)
                    )
                    if (viewModel.Photos.value.error.isNotBlank()) {
                        Text(
                            text = viewModel.Photos.value.error,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                                .align(Alignment.Center)
                        )
                    }
                    if (viewModel.Photos.value.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }

        }
    }
}