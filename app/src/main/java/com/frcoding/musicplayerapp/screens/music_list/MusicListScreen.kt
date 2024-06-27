package com.frcoding.musicplayerapp.screens.music_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.frcoding.musicplayerapp.R
import com.frcoding.musicplayerapp.model.Song

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MusicListScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MusicListViewModel = hiltViewModel()
){

    LaunchedEffect(Unit) {
        viewModel.initialize(restartApp)
    }

    Scaffold {
        val songs by viewModel.songs.collectAsState(emptyList())
        var showExitAppDialog by remember {
            mutableStateOf(false)
        }
        var showRemoveAccDialog by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            TopAppBar(
                title = {Text(stringResource(id = R.string.app_name))},
                actions = {
                    IconButton(onClick = { showExitAppDialog = true }) {
                        Icon( Icons.Filled.ExitToApp, "Exit app" )
                    }
                    IconButton(onClick = { showRemoveAccDialog = true }) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Remove account")
                    }
                }

            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp))


            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                LazyColumn {
                    items(songs, key = {it.mediaId}) { songItem ->
                        MusicItem(
                            song = songItem,
                            onActionClick = {viewModel.onMusicClick(openScreen, songItem)}
                        )
                    }
                }
            }

            if (showExitAppDialog) {
                AlertDialog(
                    title = { Text(stringResource(id = R.string.sign_out_title))},
                    text = { Text(stringResource(id = R.string.sign_out_description))},
                    onDismissRequest = {showExitAppDialog = false},
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.onSignOutClick()
                                showExitAppDialog = false
                            }
                        ) {
                            Text(text = stringResource(id = R.string.sign_out))
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { showExitAppDialog = false }
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    }
                )
            }

            if (showRemoveAccDialog) {
                AlertDialog(
                    title = { Text(text = stringResource(id = R.string.delete_acc_title))},
                    text = { Text(text = stringResource(id = R.string.delete_acc_description))},
                    onDismissRequest = { showRemoveAccDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.onDeleteAccountClick()
                                showRemoveAccDialog = false
                            }
                        ) {
                            Text(text = stringResource(id = R.string.delete_acc))
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showRemoveAccDialog = false
                            }
                        ) {
                            Text(text = stringResource(id = R.string.cancel))
                        }
                    }
                )
            }

        }


    }

}



@Composable
fun MusicItem(
    song: Song,
    onActionClick: (String) -> Unit
){
    Card (
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onActionClick(song.mediaId) }
        ) {
            Text(
                text = song.title,
                modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 12.dp)
            )
        }
    }

}