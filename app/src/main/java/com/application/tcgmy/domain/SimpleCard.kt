package com.application.tcgmy.domain

data class SimpleCard(
    val code: String = "",
    val name: String = "",
    val rarity: String = "",
    val price: Int = 0,
    val currency: Currency = Currency(),
    val image: String = "",
    val score: Int = -1
)
