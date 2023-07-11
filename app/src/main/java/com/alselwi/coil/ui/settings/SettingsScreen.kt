package com.alselwi.coil.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alselwi.coil.ui.Screen
import com.alselwi.coil.ui.bottomBar.BottomBar
import com.alselwi.coil.ui.topBar.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController : NavController = rememberNavController()){
    Scaffold(topBar = {
        TopBar()
    }, modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomBar(navController)
        }
    ){
       Column(modifier = Modifier.fillMaxWidth().padding(top = 99.dp).background(color = Color.Gray)) {
           TextButton(onClick = { navController.navigate(Screen.PrivacyScreen.route) }) {
               Text("Privacy", color = Color.White, fontSize = 18.sp)
           }
       }
    }
}