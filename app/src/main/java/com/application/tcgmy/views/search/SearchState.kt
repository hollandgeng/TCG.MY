package com.application.tcgmy.views.search

import com.application.tcgmy.domain.SimpleCard

data class SearchState(
    val cards: List<SimpleCard> = emptyList(),
    val sortedCards: Map<String, List<SimpleCard>> = emptyMap(),
    val isLoading: Boolean = false,
//    val selectedCards
)
