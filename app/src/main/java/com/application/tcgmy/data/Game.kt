package com.application.tcgmy.data

import com.application.tcgmy.constants.GameTitle
import java.util.UUID


data class Game (
    val id : UUID = UUID.randomUUID(),
    val code : GameTitle = GameTitle.Default,
    val title : String = "",
    val imageUrl : String? = null
)

val GameList = listOf<Game>(
    Game(title = "Yu-Gi-Oh!", imageUrl = "https://yuyu-tei.jp/images/gamelogo/ygo.svg"),
    Game(title = "Pokemon", imageUrl = "https://yuyu-tei.jp/images/gamelogo/poc.svg"),
    Game(title = "Card Fight!! Vanguard", imageUrl = "https://yuyu-tei.jp/images/gamelogo/vg.svg"),
    Game(title = "One Piece Card Game", imageUrl = "https://yuyu-tei.jp/images/gamelogo/opc.svg"),
    Game(title = "Weiss Schwarz", imageUrl = "https://yuyu-tei.jp/images/gamelogo/ws.svg")
)