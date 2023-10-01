package com.application.tcgmy.views.drawer

import com.application.tcgmy.data.Game

data class DrawerState(
    val games: List<Game> = emptyList()
)