package com.frcoding.musicplayerapp.screens.music_list

import com.frcoding.musicplayerapp.MUSIC_ID
import com.frcoding.musicplayerapp.MUSIC_SCREEN
import com.frcoding.musicplayerapp.SPLASH_SCREEN
import com.frcoding.musicplayerapp.model.Song
import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.model.service.StorageService
import com.frcoding.musicplayerapp.screens.MusicAppViewModel
import javax.inject.Inject

class MusicListViewModel @Inject constructor(
    private val accountService: AccountService,
    storageService: StorageService
): MusicAppViewModel() {

    val songs = storageService.songs

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(SPLASH_SCREEN)
            }
        }
    }

    fun onMusicClick(openScreen: (String) -> Unit, song: Song) {
        openScreen("$MUSIC_SCREEN?$MUSIC_ID=${song.mediaId}")
    }

    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteAccountClick() {
        launchCatching {
            accountService.deleteAccount()
        }
    }

}