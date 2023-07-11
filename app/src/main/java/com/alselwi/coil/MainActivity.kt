package com.alselwi.coil

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alselwi.coil.ads.BannerAds
import com.alselwi.coil.ui.PhotosScreen
import com.alselwi.coil.ui.Screen
import com.alselwi.coil.ui.UserPhotosScreen
import com.alselwi.coil.ui.search.Search
import com.alselwi.coil.ui.settings.PrivacyScreen
import com.alselwi.coil.ui.settings.SettingsScreen
import com.alselwi.coil.ui.theme.FlickrApiTheme
import com.alselwi.coil.ui.userPhotos.ImageDetails
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,
                        startDestination = Screen.UsersPhotos.route ){
                        composable(route = Screen.UsersPhotos.route){
                            PhotosScreen(navController = navController, context = LocalContext.current)
                        }
                        composable(route = Screen.UserPhotos.route+"/{user_id}"){
                            UserPhotosScreen(navController)
                        }

                        composable(
                            route = Screen.PhotoDetails.route + "/{id}/{owner}/{farm}/{server}/{secret}",
                        ) {
                            it.arguments?.let {link->
                                ImageDetails(
                                    link.getString("id")!!,
                                    link.getString("owner")!!,
                                    link.getString("farm")!!,
                                    link.getString("server")!!,
                                    link.getString("secret")!!,
                                    navController = navController
                                )
                            }
                        }
                        composable(route = Screen.SearchScreen.route){
                            Search(navController = navController)
                        }
                        composable(route = Screen.SettingsScreen.route){
                            SettingsScreen(navController = navController)
                        }
                        composable(route = Screen.PrivacyScreen.route){
                            PrivacyScreen(navController = navController)
                        }
                    }
                }
            }
        }
        MobileAds.initialize(this, object : OnInitializationCompleteListener {
            override fun onInitializationComplete(initializationStatus: InitializationStatus) {

            }
        })
    }
}
