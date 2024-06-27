package com.frcoding.musicplayerapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frcoding.musicplayerapp.screens.music_list.MusicListScreen
import com.frcoding.musicplayerapp.screens.sign_in.SignInScreen
import com.frcoding.musicplayerapp.screens.sign_up.SignUpScreen
import com.frcoding.musicplayerapp.screens.splash.SplashScreen

@Composable
fun MusicApp() {
    val appState = rememberAppState()

    Scaffold { innerPaddingModifier ->
        NavHost(
            navController = appState.navController as NavHostController,
            startDestination = SPLASH_SCREEN,
            modifier = Modifier.padding(innerPaddingModifier)
        ){
            musicGraph(appState)
        }

    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        MusicAppState(navController)
    }

fun NavGraphBuilder.musicGraph(appState: MusicAppState) {

    composable(MUSIC_LIST_SCREEN) {
        MusicListScreen(
            restartApp = { route -> appState.clearAndNavigate(route) } ,
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

}