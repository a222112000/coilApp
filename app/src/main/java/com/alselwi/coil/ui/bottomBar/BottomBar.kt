package com.alselwi.coil.ui.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alselwi.coil.ui.Screen

@Composable
fun BottomBar(navController: NavController = rememberNavController()){
    var nav by remember {
        mutableStateOf("Home")
    }
    BottomAppBar() {
        NavigationBarItem(selected = nav == "Home"
            , onClick = {
                navController.navigate(Screen.UsersPhotos.route)
            },
            label = { Text(text = "Home") },
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = null) }
        )
        NavigationBarItem(selected = nav == "Search"
            , onClick = {
                navController.navigate(Screen.SearchScreen.route)
            },
            label = { Text(text = "Search") },
            icon = { Icon(imageVector = Icons.Default.Search,
                contentDescription = null) }
        )
        NavigationBarItem(selected = nav == "Settings"
            , onClick = { navController.navigate(Screen.SettingsScreen.route) },
            label = { Text(text = "Settings") },
            icon = { Icon(imageVector = Icons.Default.Settings,
                contentDescription = null) }
        )
    }
}