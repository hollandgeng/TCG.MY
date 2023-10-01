package com.application.tcgmy.constants.internal

import com.application.tcgmy.constants.GameTitle

data class SimpleCard(
    val code: String = "",
    val name: String = "",
    val rarity: String = "",
    val price: Int = 0,
    val currency: Currency = Currency(),
    val image: String = "",
    val score: Int = -1,
    val game: GameTitle = GameTitle.Default
)
