package com.frcoding.musicplayerapp.model.service

import com.frcoding.musicplayerapp.model.Song
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val songs: Flow<List<Song>>
}