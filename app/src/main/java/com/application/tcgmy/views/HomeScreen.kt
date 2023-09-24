package com.application.tcgmy.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.application.tcgmy.views.drawer.TcgDrawer
import com.application.tcgmy.views.search.SearchScreen
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
            Box(modifier = Modifier.padding(paddingValues)) {
                SearchScreen()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun HomeView_Preview() {
    HomeView()
}




