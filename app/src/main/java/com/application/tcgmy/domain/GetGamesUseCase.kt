package com.application.tcgmy.domain

import com.application.tcgmy.constants.GameTitle
import com.application.tcgmy.data.Game

class GetGamesUseCase(
    private val cardClient: CardClient
) {

    suspend fun execute(): List<Game> {
        return cardClient
            .getGames()
    }
}