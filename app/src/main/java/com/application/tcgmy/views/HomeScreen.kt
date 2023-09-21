package com.application.tcgmy.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.application.tcgmy.data.GameList
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()


    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        TcgDrawer(onMenuIconClicked = {
            coroutine.launch {
                drawerState.close()
            }
        })
    }) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutine.launch {
                                if (drawerState.isOpen)
                                    drawerState.close()
                                else
                                    drawerState.open()
                            }
                        })
                        {
                            Image(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Hamburger Menu"
                            )
                        }
                    },
                    title = { Text("TCG") },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
                )
            }
        )
        { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeView_Preview() {
    HomeView()
}




