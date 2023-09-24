package com.application.tcgmy.domain

import com.application.tcgmy.constants.GameTitle

class GetCardUseCase(
    private val cardClient: CardClient
) {

    suspend fun execute(query: String, game: GameTitle): List<SimpleCard> {
        return cardClient
            .getCards(query = query, games = game.code)
            .sortedBy { it.rarity }
    }
}