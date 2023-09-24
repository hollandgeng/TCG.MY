package com.application.tcgmy.domain

data class Currency(
    val code: String = "",
    val numericCode: String = "",
    val fraction: Int = 0,
    val grapheme: String = "",
    val template: String = "",
    val decimal: String = "",
    val thousand: String = ""
)
