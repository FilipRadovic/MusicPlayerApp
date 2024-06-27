package com.frcoding.musicplayerapp.model.service.impl

import com.frcoding.musicplayerapp.model.Song
import com.frcoding.musicplayerapp.model.service.AccountService
import com.frcoding.musicplayerapp.model.service.StorageService
import com.google.firebase.Firebase
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val auth: AccountService
): StorageService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val songs: Flow<List<Song>>
        get() =
            auth.currentUser.flatMapLatest { song ->
                Firebase.firestore
                    .collection(SONGS_COLLECTION)
                    .whereEqualTo(USER_ID_FIELD, song?.id)
                    .dataObjects()
            }

    companion object{
        private const val USER_ID_FIELD = "user_id"
        private const val SONGS_COLLECTION = "songs"
    }
}