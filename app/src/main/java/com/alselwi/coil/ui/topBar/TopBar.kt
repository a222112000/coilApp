package com.alselwi.coil.ui.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alselwi.coil.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row() {

                    Card(
                        modifier = Modifier
                            .padding(end = 8.dp)
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
                        modifier = Modifier.padding(top = 6.dp, end = 8.dp).fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontSize = 27.sp),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        })
}