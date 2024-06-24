package com.frcoding.musicplayerapp.screens.sign_in


import com.frcoding.musicplayerapp.MUSIC_LIST_SCREEN
import com.frcoding.musicplayerapp.SIGN_IN_SCREEN
import com.frcoding.musicplayerapp.SIGN_UP_SCREEN
import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.screens.MusicAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
): MusicAppViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String){
        email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp(MUSIC_LIST_SCREEN, SIGN_IN_SCREEN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit){
        openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
    }
}