package com.frcoding.musicplayerapp.screens.sign_up

import com.frcoding.musicplayerapp.MUSIC_LIST_SCREEN
import com.frcoding.musicplayerapp.SIGN_UP_SCREEN
import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.screens.MusicAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
): MusicAppViewModel() {

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    fun updateEmail(newEmail: String){
        email.value = newEmail
    }

    fun updatePassword(newPassword: String){
        password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String){
        confirmPassword.value = newConfirmPassword
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            if (password.value != confirmPassword.value){
                throw Exception("Password do not match")
            }

            accountService.signUp(email.value, password.value)
            openAndPopUp(MUSIC_LIST_SCREEN, SIGN_UP_SCREEN)
        }
    }

}