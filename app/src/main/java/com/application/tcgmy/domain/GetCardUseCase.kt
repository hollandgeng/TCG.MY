package com.application.tcgmy.domain

class GetCardUseCase(
    private val cardClient: CardClient
) {

    suspend fun execute(query: String): List<SimpleCard> {
        return cardClient
            .getCards(query = query)
            .sortedBy { it.rarity }
    }
}