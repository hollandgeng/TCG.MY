package com.application.tcgmy.constants.internal

import com.application.tcgmy.constants.BannedStatus

data class YgoDetailedCard (
    val code: String = "",
    val jpName: String = "",
    val engName: String = "",
    val cardType: String = "",
    val property: String = "",
    val attribute: String = "",
    val types: List<String> = emptyList(),
    val level: String = "", //level or rank
    val linkArrows: String = "",
    val attack: String = "",
    val defence: String = "",
    val link: String = "",
    val effectTypes: List<String> = emptyList(), //effect types
    val effect: String = "", //card effect
    val pendulum: Pendulum = Pendulum(),
    val status: BannedStatus = BannedStatus.UNLIMITED
)

data class Pendulum (
    val effectTypes: List<String> = emptyList(), //effect types
    val scale: String = "",
    val effect: String = "" //card effect
)