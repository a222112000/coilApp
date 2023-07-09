package com.alselwi.coil.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.alselwi.coil.R
import com.alselwi.coil.ads.BannerAds
import com.alselwi.coil.ui.Photos.PhotosViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel(),
    context: Context
) {
    val state = viewModel.Photos.value.photos?.flow?.collectAsLazyPagingItems()
    val search = viewModel.getPhotos.value
    var filteredPhotos by remember { mutableStateOf(state) }
    var isSearching by remember { viewModel.isSearching }
    var isLoading by remember {
        viewModel.isLoading
    }
    Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Row() {

                                Card(
                                    modifier = Modifier
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
                                Text(
                                    text = stringResource(R.string.Random),
                                    modifier = Modifier.padding(top = 13.dp, end = 8.dp),
                                    style = TextStyle(color = Color.Black, fontSize = 27.sp),
                                    textAlign = TextAlign.Start,
                                )
                            }
                            SearchBar(
                                hint = stringResource(id = R.string.search),

                                ) {
                                viewModel.searchItems(it)
                            }
                        }
                    })

    }, modifier = Modifier.fillMaxWidth(),
    bottomBar = {
        BannerAds()
    }
        ) {

        Surface(
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.fillMaxSize()
        ) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp), Arrangement.Center) {
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
                            } else if(!isLoading) {
                                state?.itemCount?.let {
                                    items(it) { index ->
                                        state.get(index)?.let {
                                            PhotosItem(photo = it,
                                                onUserPhotoClick = { navController.navigate(Screen.UserPhotos.route + "/${it.owner}") }
                                            )
                                        }
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

    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier,
        hint: String = "",
        onSearch: (String) -> Unit = {}
    ){
        var text  by remember {
            mutableStateOf("")
        }
        var isHintDisplay by remember {
            mutableStateOf(hint != "")
        }
        Box(modifier = modifier){
            BasicTextField(value = text, onValueChange ={
                text = it
                onSearch(it)
            },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, CircleShape)
                    .background(Color.Black, CircleShape)
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .onFocusChanged {
                        val focus = it
                        isHintDisplay = focus.isFocused && text.isNotEmpty()
                    },
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            // margin left and right
                            .fillMaxWidth()
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(size = 1.dp)
                            )
                            .padding(all = 16.dp), // inner padding
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if(text.isEmpty()){
                            Text(text = "Search...", style = TextStyle(color = Color.Gray, fontSize = 18.sp))
                        }
                        innerTextField()
                        Spacer(modifier = Modifier.width(width = 1.dp))
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search...",
                            tint = Color.Gray
                        )

                    }
                }
            )

        }
    }

@SuppressLint("DiscouragedApi")
@Composable
fun BitmapImage(bitmap: Bitmap,context: Context) {
    var res: Resources = context.resources
    var resource = res.getIdentifier(bitmap.toString(),"mipmap", context.packageName)
    var bitmap: Bitmap = BitmapFactory.decodeResource(res, resource)
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "some useful description",
    )
}
