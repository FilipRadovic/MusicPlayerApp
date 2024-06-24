package com.frcoding.musicplayerapp.screens.splash

import com.frcoding.musicplayerapp.MUSIC_LIST_SCREEN
import com.frcoding.musicplayerapp.SIGN_IN_SCREEN
import com.frcoding.musicplayerapp.SPLASH_SCREEN
import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.screens.MusicAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
): MusicAppViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(MUSIC_LIST_SCREEN, SPLASH_SCREEN)
        else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }
}