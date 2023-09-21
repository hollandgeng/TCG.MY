package com.application.tcgmy.domain

data class SimpleCard(
    val code: String,
    val name: String,
    val rarity: String,
    val price: Int,
    val currency: String,
    val image: String
)
