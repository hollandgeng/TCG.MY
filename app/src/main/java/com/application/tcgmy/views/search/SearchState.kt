package com.application.tcgmy.views.search

import com.application.tcgmy.domain.SimpleCard

data class SearchState(
    val cards: List<SimpleCard> = emptyList(),
    val isLoading: Boolean = false,
//    val selectedCards
)
