package com.frcoding.musicplayerapp

import androidx.compose.runtime.Stable
import androidx.navigation.NavController

@Stable
class MusicAppState(val navController: NavController) {
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) {launchSingleTop = true}
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) {inclusive = true}
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) {inclusive = true}
        }
    }
}