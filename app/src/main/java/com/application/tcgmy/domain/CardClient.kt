package com.application.tcgmy.domain

import com.application.tcgmy.data.Game
import com.application.tcgmy.type.GameCode

interface CardClient {

    suspend fun getGames(): List<Game>
    suspend fun getCards(query: String, games: GameCode): List<SimpleCard>
}